const admin = require("firebase-admin");

exports.create = async (req, res) => {
  try {
    const { displayName, password, email, role } = req.body;
    if (!displayName || !password || !email || !role) {
      return res.status(400).send({ message: "Missing fileds" });
    }

    const { uid } = await admin.auth().createUser({
      displayName,
      password,
      email,
    });

    await admin.auth().setCustomUserClaims(uid, { role });
    return res.status(201).send({ uid });
  } catch (err) {
    return handleError(res, err);
  }
};

exports.all = async (req, res) => {
  try {
    const listUsers = await admin.auth().listUsers();
    const users = listUsers.users.map(mapUser);
    return res.status(200).send({ users });
  } catch (err) {
    return handleError(res, err);
  }
};

exports.get = async (req, res) =>{
  try {
    const { id } = req.params;
    const user = await admin.auth().getUser(id);
    return res.status(200).send({ user: mapUser(user) });
  } catch (err) {
    return handleError(res, err);
  }
};

exports.patch = async (req, res) =>{
  try {
    const { id } = req.params;
    const { displayName, password, email, role } = req.body;

    if (!id || !displayName || !password || !email || !role) {
      return res.status(400).send({ message: "Missing fields" });
    }

    await admin.auth().updateUser(id, { displayName, password, email });

    await admin.auth().setCustomUserClaims(id, { role });

    const user = await admin.auth().getUser(id);

    return res.status(204).send({ user: mapUser(user) });
  } catch (err) {
    return handleError(res, err);
  }
};

exports.remove = async (req, res) => {
  try {
    const { id } = req.params;
    await admin.auth().deleteUser(id);
    return res.status(204).send({});
  } catch (err) {
    return handleError(res, err);
  }
};

function mapUser(user) {
  const customClaims = (user.customClaims || { role: "" });
  const role = customClaims.role ? customClaims.role : "";
  return {
    uid: user.uid,
    email: user.email || "",
    displayName: user.displayName || "",
    role,
    lastSignInTime: user.metadata.lastSignInTime,
    creationTime: user.metadata.creationTime,
  };
}

function handleError(res, err) {
  return res.status(500).send({ message: `${err.code} - ${err.message}` });
}
