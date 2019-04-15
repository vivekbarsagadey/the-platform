from flask_restplus import Resource
from healthcare import api

home = api.namespace('home', description='Intial Conrtroller')


@home.route('/', endpoint='/home')
class homecontroller(Resource):

    def get(self):
        return 'Get method intiated'

    def get1(self):
        return 'GET1 Intiated'

    def post(self):
        return 'POST method of Home controller intiated'
