const admin = require("firebase-admin");

exports.isAuthenticated = async (req, res, next) => {
  const { authorization } = req.headers;

  if (!authorization) {
    return res.status(401).send({ message: "Unauthorized" });
  }

  if (!authorization.startsWith("Bearer")) {
    return res.status(301).send({ message: "Unauthorized" });
  }

  const split = authorization.split(" ");
  if (split.length !== 2) {
    return res.status(401).send({ message: "Unauthorized" });
  }

  const token = split[1];

  try {
    const decodeToken = await admin.auth().verifyIdToken(token);
    console.log("decodeToken", JSON.stringify(decodeToken));
    res.locals = {
      ...res.locals,
      uid: decodeToken.uid,
      role: decodeToken.role,
      email: decodeToken.email,
    };
    return next();
  } catch (err) {
    console.error(`${err.code} -  ${err.message}`);
    return res.status(403)
        .send({ "error_code": err.code, "message": err.message });
  }
};
