from flask import request
from flask_restplus import Resource
from healthcare import api
from healthcare.resources.disease.diabetes.controllers.input_handler import User
from healthcare.resources.disease.diabetes.controllers.user_input import user_fields
from healthcare.resources.disease.diabetes.model.models import AllModels

prediction = api.namespace('api/diabetes/all', description='Operations related to diabetes')


@prediction.route('/', endpoint='/all')
class DiabetesController(Resource):

    def get(self):
        AllModels().train()
        return 'trained the model'

    @api.expect(user_fields, validate=False)
    @api.doc(responses={
        200: 'Prediction Given',
        400: 'Validation Error'
    })
    def post(self):
        json_data = request.get_json(force=True)
        user = User(json_data)
        print("user is ", user)
        userDataFrame = user.getframe()
        return AllModels().predict(userDataFrame)
