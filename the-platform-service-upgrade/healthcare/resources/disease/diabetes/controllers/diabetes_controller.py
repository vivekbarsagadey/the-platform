from flask import request
from flask_restplus import Resource
from healthcare import api
from healthcare.resources.disease.diabetes.controllers.input_handler import User
from healthcare.resources.disease.diabetes.controllers.user_input import user_fields
from healthcare.resources.disease.diabetes.model.models import AllModels
from healthcare.resources.disease.diabetes.model.nn_model import neuralNet

prediction = api.namespace('api/diabetes/all', description='Operations related to Diabetes GET Method train the Model')


@prediction.route('/', endpoint='/all')
class DiabetesController(Resource):

    def get(self):
        AllModels().train()
        neuralNet().trainnet()
        return 'trained the model'

    @api.expect(user_fields, validate=False)
    @api.doc(responses={
        200: 'Prediction Given',
        400: 'Validation Error'
    })
    def post(self):
        json_data = request.get_json(force=True)
        user = User(json_data)
        userdata = user.getframe
        out = neuralNet().netpredict(userdata)
        temp = AllModels().predict(userdata)
        result = temp + out
        return result