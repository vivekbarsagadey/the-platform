from flask import request
from flask_restplus import Resource

from com.platform.health.diabetes.domain.registration import Registration
from com.platform.health.diabetes.domain.user import User
from com.platform.health.diabetes.model.k_nearest_neighbors import KNearestNeighborsModel
from com.platform.health.diabetes.model.models import AllModels
from com.platform.health.diabetes.services.data_handler import DiabetesDataSet


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
        user = User(json_data)
        print("user is ",user)
        mod = AllModels()
        user.save()
        userDataFrame=user.getFrame()
        if model_name == 'knn':
             return KNearestNeighborsModel().predict()
        else:
             return AllModels().predict(userDataFrame)

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

class RegistrationController(Resource):

    def get(self):
        return  "Registration controller is called"


    def post(self):
        json_data = request.get_json(force=True)
        registration=Registration(json_data)
        print("registration detail",registration)
        registration.details()


class DiabetesTrainingController(Resource):

    def get(self):
        AllModels().train()
        return "Thanks"
