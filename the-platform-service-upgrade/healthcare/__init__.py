from flask import Flask
from flask_cors import CORS
from flask_restplus import Api

app = Flask(__name__)
CORS(app)

app.config['SWAGGER_UI_DOC_EXPANSION'] = 'List'

api = Api(app, title='the-platform', description='Created by WhizIT', default='the-platform',
          default_label='Controllers',
          validate=True)

from healthcare.resources import home
