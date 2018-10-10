from flask_restful import Resource, Api
from flask import render_template,make_response
class HomeController(Resource):
    def get(self):
        headers = {'Content-Type': 'text/html'}
        return make_response(render_template('home.html'),headers)
