const admin = require("firebase-admin");
const { body, validationResult } = require("express-validator");
const mime = require("mime-types");
const http = require("https");

exports.validators = [
  body("alamat").notEmpty().withMessage("alamat harus diisi"),
  body("deskripsi").notEmpty().withMessage("deskripsi harus diisi"),
  body("foto").notEmpty().withMessage("foto harus diisi"),
  body("kondisi_kerusakan").notEmpty()
      .withMessage("kondisi_kerusakan harus diisi"),
  body("latitude").notEmpty()
      .withMessage("latitude harus diisi").isFloat("Harus barupa angka"),
  body("longitude").notEmpty()
      .withMessage("latitude harus diisi").isFloat("Harus barupa angka"),
];

exports.create = async (req, res) =>{
  try {
    const { authorization } = req.headers;
    const split = authorization.split(" ");
    const token = split[1];
    const decodeToken = await admin.auth().verifyIdToken(token);

    const errors = validationResult(req);
    if (!errors.isEmpty()) {
      return res.status(400).json({ errors: errors.array() });
    }
    // eslint-disable-next-line prefer-const
    let { foto, ...data } = req.body;
    const mimeType = base64MimeType(req.body.foto);
    data.id_pelapor = decodeToken.uid;


    const { id } = await admin.firestore().collection("laporans").add(data);

    const publicUrl = await uploadAndGetPublicFile(
        id +"." + mime.extension(mimeType),
        foto
    );

    data.foto = publicUrl;

    await admin.firestore().collection("laporans").doc(id).update(data);

    requestPrediction(id, foto, data);

    return res.status(201).send(JSON.stringify({ id }));
  } catch (err) {
    return handleError(res, err);
  }
};


exports.all = async (req, res) => {
  try {
    const snapshot = await admin.firestore().collection("laporans").get();

    // eslint-disable-next-line prefer-const
    let data = [];
    snapshot.forEach((doc) => {
      const id = doc.id;
      const d = doc.data();

      data.push({ id, ...d });
    });

    return res.status(200).send(JSON.stringify(data));
  } catch (err) {
    return handleError(res, err);
  }
};

exports.get = async (req, res) => {
  try {
    const snapshot = await admin.firestore()
        .collection("laporans").doc(req.params.id).get();

    const id = snapshot.id;
    const data = snapshot.data();

    if (!data) {
      return res.status(404).send("data not found");
    }

    return res.status(200).send(JSON.stringify({ id: id, ...data }));
  } catch (err) {
    return handleError(res, err);
  }
};


exports.remove = async (req, res) => {
  try {
    // delete Image from Cloud Storage
    const snapshot = await admin.firestore()
        .collection("laporans").doc(req.params.id).get();

    // eslint-disable-next-line camelcase
    let { foto, predicted_foto } = snapshot.data();
    foto = foto.replace(/.+public\//, "");

    // eslint-disable-next-line camelcase
    predicted_foto = predicted_foto.replace(/.+public\//, "");
    deleteImageFromStorage(foto);
    deleteImageFromStorage(predicted_foto);

    // delete data from Firestore
    await admin.firestore()
        .collection("laporans").doc(req.params.id).delete();

    return res.status(204).send();
  } catch (err) {
    return handleError(res, err);
  }
};

exports.patch = async (req, res) =>{
  try {
    const { authorization } = req.headers;
    const split = authorization.split(" ");
    const token = split[1];
    const decodeToken = await admin.auth().verifyIdToken(token);

    const { id } = req.params;
    const data = req.body;

    data.id_petugas = decodeToken.uid;
    await admin.firestore().collection("laporans").doc(id).update(data);

    const snapshot = await admin.firestore()
        .collection("laporans").doc(id).get();
    const updatedData = snapshot.data();

    return res.status(201).send({ updatedData });
  } catch (err) {
    return handleError(res, err);
  }
};

const deleteImageFromStorage = async (fileName) => {
  await admin.storage().bucket().file("public/"+fileName).delete();
};

const uploadAndGetPublicFile = async (fileName, data) => {
  const file = admin.storage().bucket().file("public/"+fileName);

  const fileOptions = {
    public: true,
    resumable: false,
    metadata: { contentType: base64MimeType(data) },
    validation: false,
  };
  const base64encodedString = data.replace(/^data:\w+\/\w+;base64,/, "");
  const fileBuffer = Buffer.from(base64encodedString, "base64");
  await file.save(fileBuffer, fileOptions);
  const publicUrl = `https://storage.googleapis.com/tensile-ship-312415.appspot.com/public/${fileName}`;
  return publicUrl;
};

function base64MimeType(encoded) {
  let result = null;

  if (typeof encoded !== "string") {
    return result;
  }

  const mime = encoded.match(/data:([a-zA-Z0-9]+\/[a-zA-Z0-9-.+]+).*,.*/);

  if (mime && mime.length) {
    result = mime[1];
  }

  return result;
}

function handleError(res, err) {
  return res.status(500).send({ message: `${err.code} - ${err.message}` });
}

const requestPrediction = (id, foto, data) =>{
  foto = JSON.stringify({ foto });

  const options = {
    hostname: "predictimage-ivzsbvjqlq-et.a.run.app",
    port: 443,
    method: "POST",
    headers: {
      "Content-Type": "application/json",
      "Content-Length": foto.length,
    },
  };

  const predictReq = http.request(options, (response) => {
    console.log(`statusCode: ${response.statusCode}`);
    let responseData = "";
    response.on("data", (d) => {
      responseData += d;
    });

    response.on("end", async () =>{
      try {
        responseData = JSON.parse(responseData);
        const fileFoto = responseData.predicted_image;
        const fileMimeType = base64MimeType(fileFoto);
        const fileExtention = mime.extension(fileMimeType);
        const predictedUrl = await uploadAndGetPublicFile(
            id + "-predicted." + fileExtention,
            fileFoto
        );
        data.predicted_foto = predictedUrl;
        await admin.firestore().collection("laporans").doc(id).update(data);
        console.log("Image Prediction Complete: "+ predictedUrl);
      } catch ( err ) {
        console.error( err );
      }
    });
  });

  predictReq.on("error", (error) => {
    console.error(error);
  });

  predictReq.write(foto);
  predictReq.end();
};
