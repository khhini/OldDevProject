const { create, all, get, patch, remove, getLaporan } = require("./users");
const express = require("express");
const { isAuthenticated } = require("../auth/authentitated");
const { isAuthorized } = require("../auth/isAuthorized");
const router = new express.Router();
const getUserLaporan = new express.Router();

router.post("/", create);

router.get("/", [
  isAuthenticated,
  isAuthorized({ hasRole: ["admin"], alloSameUser: true }),
  all,
]);

router.get("/:id", [
  isAuthenticated,
  isAuthorized({ hasRole: ["admin", "user"], alloSameUser: true }),
  get,
]);

router.patch("/:id", [
  isAuthenticated,
  isAuthorized({ hasRole: ["admin", "user"], alloSameUser: true }),
  patch,
]);

router.delete("/:id", [
  isAuthenticated,
  isAuthorized({ hasRole: ["admin", "user"], alloSameUser: true }),
  remove,
]);

getUserLaporan.get("/", [
  isAuthenticated,
  isAuthorized({ hasRole: ["admin", "user"], alloSameUser: true }),
  getLaporan,
]);

module.exports = { router, getUserLaporan };
