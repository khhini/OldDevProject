const { create, all, validators, get, remove } = require("./laporan");
const express = require("express");
const { isAuthenticated } = require("../auth/authentitated");
const { isAuthorized } = require("../auth/isAuthorized");
const router = new express.Router();

router.post("/", validators, [
  isAuthenticated,
  isAuthorized({ hasRole: ["admin", "user"] }),
  create,
]);

router.get("/", all);

router.get("/:id", get);

router.delete("/:id", [
  isAuthenticated,
  isAuthorized({ hasRole: ["admin", "user"] }),
  remove,
]);

module.exports = router;
