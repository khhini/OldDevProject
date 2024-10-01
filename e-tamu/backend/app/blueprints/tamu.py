from flask import Blueprint, jsonify, request

from ..extention import mongo

from bson.json_util import dumps

from bson.objectid import ObjectId

tamu = Blueprint('tamu',__name__)

@tamu.route('/tamu', methods=['POST'])
def add_tamu():
    _json = request.json
    _id_event = request.json['id_event']
    _data = request.json['data_tamu']

    if _id_event and _data and request.method == 'POST':
        mongo.db.tamu.insert_one({
            'id_event':ObjectId(_id_event),
            'data_tamu':_data
        })

        resp = jsonify('OK')
        resp.status_code = 200

        return resp

    return not_found()

@tamu.route('/tamu/<id_event>', methods=['GET'] )
def get_tamus(id_event):
    tamus = mongo.db.tamu.find({'id_event':ObjectId(id_event)})
    resp = dumps(tamus)
    return resp

@tamu.route('/tamu/<id_event>/<id_tamu>', methods=['GET'])
def get_tamu(id_event, id_tamu):
    tamu = mongo.db.tamu.find_one({
        '_id':ObjectId(id_tamu),
        'id_event':ObjectId(id_event)
        })
    resp = dumps(tamu)
    return resp

@tamu.route('/tamu/<id_tamu>', methods=['PUT'])
def update_tamu(id_tamu):
    _id = id_tamu
    _json = request.json
    _data = request.json['data_tamu']

    if _id and _data and request.method == 'PUT':
        mongo.db.tamu.update_one(
            {'_id': ObjectId(_id)},
            {'$set':{
                'data_tamu':_data
            }}
        )
        
        resp = jsonify('OK')
        resp.status_code = 200
        return resp

    return not_found()

@tamu.route('/tamu/<id_event>', methods=['DELETE'])
def del_tamu(id_event):
    mongo.db.tamu.delete_many({'id_event':ObjectId(id_event)})
    resp = jsonify('OK')
    resp.status_code = 200
    return resp
