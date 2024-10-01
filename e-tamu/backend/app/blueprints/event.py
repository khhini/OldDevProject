from flask import Blueprint, jsonify, request

from ..extention import mongo

from bson.json_util import dumps

from bson.objectid import ObjectId

event = Blueprint('event',__name__)

@event.route('/event', methods=['POST'])
def add_event():
    _json = request.json
    _id_user = ObjectId(_json['id_user'])
    _nama = _json['nama_event']
    _lokasi = _json['lokasi_event']
    _tgl = _json['tgl_event']
    _jam_mulai = _json['jam_mulai']
    _jam_selesai = _json['jam_selesai']
    _form = _json['format_form']

    if _id_user and _nama and _lokasi and _tgl and _jam_mulai and _jam_selesai and _form and request.method == 'POST':
        mongo.db.event.insert_one({
            'id_user':_id_user,
            'nama_event':_nama,
            'lokasi_event':_lokasi,
            'tgl_event':_tgl,
            'jam_mulai':_jam_mulai,
            'jam_selesai':_jam_selesai,
            'format_form':_form
        })

        resp = jsonify('OK')
        resp.status_code = 200

        return resp
    
    return not_found()

@event.route('/event/<id_user>', methods=['GET'])
def get_events(id_user):
    events = mongo.db.event.find({'id_user':ObjectId(id_user)})
    resp = dumps(events)
    return resp

@event.route('/event/<id_user>/<id_event>', methods=['GET'])
def get_event(id_user, id_event):
    event = mongo.db.event.find_one({
        '_id':ObjectId(id_event),
        'id_user':ObjectId(id_user)
        })
    resp = dumps(event)
    return resp

@event.route('/event/<id_event>',methods=['PUT'])
def update_event(id_event):
    _id = id_event
    _json = request.json
    _nama = _json['nama_event']
    _lokasi = _json['lokasi_event']
    _tgl = _json['tgl_event']
    _jam_mulai = _json['jam_mulai']
    _jam_selesai = _json['jam_selesai']
    _form = _json['format_form']

    if _nama and _lokasi and _tgl and _jam_mulai and _jam_selesai and _form and request.method == 'PUT':
        mongo.db.event.update_one(
            {'_id':ObjectId(_id)},
            {'$set':{
                'nama_event':_nama,
                'lokasi_event':_lokasi, 
                'tgl_event':_tgl,
                'jam_mulai':_jam_mulai,
                'jam_selesai':_jam_selesai,
                'format_form':_form
            }}
        )
        resp = jsonify('OK')
        resp.status_code = 200
        return resp

@event.route('/event/<id_event>',methods=['DELETE'])
def del_user(id_event):
    mongo.db.event.delete_one({'_id':ObjectId(id_event)})
    resp = jsonify('OK')
    resp.status_code = 200
    return resp

@event.errorhandler(404)
def not_found(error=None):
    message = {
        'status': 404,
        'message': 'Not Found'
    }
    resp = jsonify(message)
    resp.status_code = 404

    return resp
 