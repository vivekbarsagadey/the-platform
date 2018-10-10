from flask import Flask, jsonify, request
from flask_restful import Resource, reqparse
from com.platform.health.diabetes.model.models import AllModels
import pandas as pd

from com.platform.health.diabetes.domain.user import User
from com.platform.health.diabetes.model.k_nearest_neighbors import KNearestNeighborsModel
from com.platform.health.diabetes.services.data_handler import DiabetesDataSet

from com.platform.health.diabetes.parser import patient_data_parser
#parser = reqparse.RequestParser()
#parser.add_argument('firstName', type=str, location='json')

class DiabetesController(Resource):
    '''@swagger.doc({
        'tags': ['DiabetesController'],
        'description': 'Returns a Diabetes data',
        'parameters': [
            {
                'model_name': 'all',
                'description': 'which model is going to use'
            }
        ],
        'responses': {
            '200': {
                'description': 'User',
                'examples': {
                    'application/json': {
                        'data': 'all'
                    }
                }
            }
        }
    })'''
    def get(self, model_name = "all"):
        if model_name == 'knn':
            return KNearestNeighborsModel().predict()
        else:
            return AllModels().predictAll()


    def post(self , model_name = "all"):
        json_data = request.get_json(force=True)
        print(type(json_data))
        user = User(json_data)
        print("user is ",user)
        mod = AllModels()
        #user.save()
        userDataFrame=user.getFrame()
        if model_name == 'knn':
             return KNearestNeighborsModel().predict()
        else:
             return AllModels().predict(userDataFrame)
        #return {"hello":"world"}
class DiabetesDataTestController(Resource):
    def post(self ):
        json_data = request.get_json(force=True)
        #args = parser.parse_args()
        print(json_data)
        #print(args['firstName'])
        return {"name":"test"}

class DiabetesDataSetController(Resource):
    def get(self , name = "features"):
        if name == 'features':
            return DiabetesDataSet().getColumns()
        elif name == 'shape' :
            return DiabetesDataSet().getDataShape()
        elif name == 'describe' :
            return DiabetesDataSet().getDescribe()
        elif name == 'data' :
            return DiabetesDataSet().getData()
        else:
            return DiabetesDataSet().getData()


class DiabetesModelFeaturesController(Resource):
    def get(self):
        print("Features set is called")
        patient_data_parser.PatientDataParser().getPatientListFromFile()

