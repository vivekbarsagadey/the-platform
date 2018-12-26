from flask import Flask,Blueprint
from flask_restplus import Api
from werkzeug.contrib.fixers import ProxyFix
from com.platform.health.diabetes.data.user_data import UserFileuploadscontroller
from flask_cors import CORS
from com.platform.core.web.home import HomeController
from com.platform.health.diabetes.web.diabetes import DiabetesController, DiabetesDataSetController, DiabetesDataTestController, RegistrationController,DiabetesTrainingController

app = Flask(__name__)
CORS(app)

#Swagger UI Commented
#blueprint=Blueprint('api',__name__)
#api = Api(blueprint , catch_all_404s=True,doc='/documentation')
#app.register_blueprint(blueprint)
#api = Api(app,api_version='0.0', api_spec_url='/api/swagger')
#app.config['SWAGGER_UI_JSONEDITOR']=True

api = Api(app, catch_all_404s=True)

api.add_resource(HomeController, '/', endpoint="home")
api.add_resource(DiabetesController, '/api/diabetes/<model_name>', endpoint="diabetes")
api.add_resource(DiabetesDataSetController, '/api/diabetes/dataset/<name>', endpoint="diabetes/dataset")
api.add_resource(UserFileuploadscontroller,'/api/diabetes/dataset/upload',endpoint="dataset/upload")
api.add_resource(RegistrationController,'/api/diabetes/registration',endpoint="diabetes/registration")
api.add_resource(DiabetesTrainingController,'/api/diabetes/train', endpoint="diabetes/train")

if __name__ == '__main__':
    app.run(debug=True,port=5000,host="212.83.186.174")