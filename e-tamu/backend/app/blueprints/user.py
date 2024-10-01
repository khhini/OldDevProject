from flask import Blueprint, jsonify, request

from ..extention import mongo

from bson.json_util import dumps

from bson.objectid import ObjectId

from werkzeug.security import generate_password_hash

import pymongo

user = Blueprint('user', __name__)

@user.route('/user', methods=['POST'])
def add_users():
    _json = request.json
    _email = _json['email']
    _pass = _json['password']
    _nama = _json['nama']
    _no_hp = _json['no_hp']
    _level = _json['level']

    if _email and _pass and _nama and _no_hp and _level and request.method == 'POST':
        _hash = generate_password_hash(_pass)
        try:
            res = mongo.db.users.insert_one({
                'email': _email,
                'password':_hash,
                'nama':_nama,
                'no_hp':_no_hp,
                'level':_level
            })
            resp = jsonify('OK')
            resp.status_code = 200
            
            return resp
        except pymongo.errors.DuplicateKeyError as e:
            pass
        
    return not_found()

@user.route('/user', methods=['GET'])
def get_users():
    users = mongo.db.users.find()
    resp = dumps(users)
    return resp

@user.route('/user/<id>', methods=['GET'])
def get_user(id):
    user = mongo.db.users.find_one({'_id':ObjectId(id)})
    resp = dumps(user)
    return resp

@user.route('/user/<id>', methods=['PUT'])
def update_user(id):
    _id = id
    _json = request.json
    _email = _json['email']
    _pass = _json['password']
    _nama = _json['nama']
    _no_hp = _json['no_hp']
    _level = _json['level']

    if _email and _pass and _nama and _level and _no_hp and _id and request.method == 'PUT':
        _hash = generate_password_hash(_pass)

        mongo.db.users.update_one(
            {'_id': ObjectId(_id)},
            {"$set":{
                'email': _email,
                'password':_hash,
                'nama':_nama,
                'no_hp':_no_hp,
                'level':_level
            }}
        )
        resp = jsonify('OK')
        resp.status_code = 200
        return resp
    else:
        mongo.db.users.update_one(
            {'_id': ObjectId(_id)},
            {"$set":{
                'email': _email,
                'nama':_nama,
                'no_hp':_no_hp,
                'level':_level
            }}
        )
        resp = jsonify('OK')
        resp.status_code = 200
        return resp

@user.route('/user/<id>',methods=['DELETE'])
def del_user(id):
    mongo.db.users.delete_one({'_id':ObjectId(id)})
    resp = jsonify('OK')
    resp.status_code = 200
    return resp

@user.errorhandler(404)
def not_found(error=None):
    message = {
        'status': 404,
        'message': 'Not Found'
    }
    resp = jsonify(message)
    resp.status_code = 404

    return resp


