from flask import Flask

from .extention import mongo

from .blueprints import user, auth, event, tamu

from flask_cors import CORS

def create_app(config_object='app.setting'):
    app = Flask(__name__)
    cors = CORS(app, resources={r"/*": {"origins": "*"}})


    app.config.from_object(config_object)
    mongo.init_app(app)

    app.register_blueprint(user.user)
    app.register_blueprint(auth.auth)
    app.register_blueprint(event.event)
    app.register_blueprint(tamu.tamu)

    return app
