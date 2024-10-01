from flask import Blueprint, jsonify, request

from ..extention import mongo

from bson.json_util import dumps

from werkzeug.security import check_password_hash

auth = Blueprint('auth',__name__)

@auth.route('/auth',methods=['POST'])
def user_auth():
    _json = request.json
    _email = _json['email']
    _pass = _json['password']
    
    if _email and _pass and request.method == 'POST':
        user = mongo.db.users.find_one({'email':_email})
        
        if user == None:
            resp = jsonify("NOT_FOUND")
            return resp
        
        if(check_password_hash(user['password'],_pass)):
            resp = dumps(user)
            return resp
        
        resp = jsonify("WRONG_PASSWORD")
        return resp
    return not_found()

@auth.errorhandler(404)
def not_found(error=None):
    message = {
        'status': 404,
        'message': 'Not Found'
    }
    resp = jsonify(message)
    resp.status_code = 404

    return resp


