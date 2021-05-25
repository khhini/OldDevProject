const admin = require("firebase-admin");
const { body, validationResult } = require("express-validator");

exports.validators = [
  body("alamat").notEmpty().withMessage("alamat harus diisi"),
  body("deskripsi").notEmpty().withMessage("rating_rs harus diisi"),
  body("foto").notEmpty().withMessage("foto harus diisi"),
  body("kondisi_kerusakan").notEmpty()
      .withMessage("kondisi_kerusakan harus diisi"),
  body("latitude").notEmpty()
      .withMessage("latitude harus diisi").isFloat("Harus barupa angka"),
  body("longitude").notEmpty()
      .withMessage("latitude harus diisi").isFloat("Harus barupa angka"),
];

exports.create = async (req, res) =>{
  const errors = validationResult(req);
  if (!errors.isEmpty()) {
    return res.status(400).json({ errors: errors.array() });
  }
  const data = req.body;

  const { id } = await admin.firestore().collection("laporans").add(data);

  return res.status(201).send(JSON.stringify({ id }));
};


exports.all = async (req, res) => {
  const snapshot = await admin.firestore().collection("laporans").get();

  let data = [];
  snapshot.forEach((doc) => {
    const id = doc.id;
    const d = doc.data();

    data.push({ id, ...d });
  });

  return res.status(200).send(JSON.stringify(data));
};

exports.get = async (req, res) => {
  const snapshot = await admin.firestore()
      .collection("laporans").doc(req.params.id).get();

  const id = snapshot.id;
  const data = snapshot.data();

  if (!data) {
    return res.status(404).send("data not found");
  }

  return res.status(200).send(JSON.stringify({ id: id, ...data }));
};


exports.remove = async (req, res) => {
  await admin.firestore()
      .collection("laporans").doc(req.params.id).delete();
  return res.status(200).send();
};
