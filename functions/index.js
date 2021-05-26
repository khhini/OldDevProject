const functions = require("firebase-functions");
const admin = require("firebase-admin");
const express = require("express");
const cors = require("cors");
const bodyParser = require("body-parser");
const userRouter = require("./users/route-config");
const laporanRouter = require("./laporans/route-config");


const serviceAccount = require("../tensile-ship-312415-44c2e1f33da2.json");

// // Create and Deploy Your First Cloud Functions
// // https://firebase.google.com/docs/functions/write-firebase-functions
//

// admin.initializeApp();
admin.initializeApp({
  credential: admin.credential.cert(serviceAccount),
  storageBucket: "gs://tensile-ship-312415.appspot.com",
});

const app = express();
app.use(bodyParser.json());
app.use(cors({ origin: true }));


app.use("/users", userRouter);
app.use("/laporans", laporanRouter);

exports.api = functions.https.onRequest(app);
