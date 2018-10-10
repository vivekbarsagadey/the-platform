import pandas as pd
import numpy as np
import matplotlib.pyplot as plt
import os
import codecs, json

from sklearn.model_selection import train_test_split
from sklearn.model_selection import cross_val_score
from sklearn.metrics import accuracy_score
BASE_DIR = os.path.abspath(os.path.dirname(__name__))
NEXT_FOLDER_PATH="/com/platform/health/diabetes/data/"
FULL_PATH =BASE_DIR +  NEXT_FOLDER_PATH

df = pd.read_csv(os.path.join(FULL_PATH,'diabetes.csv'))
feature_names = ['Pregnancies', 'Glucose', 'BloodPressure', 'SkinThickness', 'Insulin', 'BMI', 'DiabetesPedigreeFunction', 'Age']


def cleanData() :
    df.isna().sum()
    df.isnull().sum()

def show():
    print("Diabetes data set columns :{}".format(df.columns))
    #print("Diabetes data set describe :{}".format(df.describe))

def init():
    #cleanData()
    show()

init()


class NumpyEncoder(json.JSONEncoder):
    def default(self, obj):
        if isinstance(obj, np.ndarray):
            return obj.tolist()
        return json.JSONEncoder.default(self, obj)

class DiabetesDataSet():

    def getDataSet(self):
        return df

    def getData(self):
        #print(json.dumps({'columns' : diabetes.columns} , cls=NumpyEncoder))
        return self.getDataSet().to_json(orient='split')

    def getDataShape(self):
        #return  json.dumps({'shape' : self.getData().shape} , cls=NumpyEncoder)
        print("Diabetes data set dimensions :{}".format(self.getDataSet().shape))
        return {'shape' : self.getDataSet().shape}

    def getDescribe(self):
        #return  json.dumps({'shape' : self.getData().shape} , cls=NumpyEncoder)
        print("Diabetes data set dimensions :{}".format(self.getDataSet().describe))
        return {'describe' : self.getDataSet().columns}


    def getX(self):
        return self.getDataSet()[feature_names]

    def getXByDataFrame(self , df):
        return df[feature_names]


    def getY(self):
        return self.getDataSet().Outcome

    def getColumns(self):
        #print(json.dumps({'columns' : diabetes.columns} , cls=NumpyEncoder))
        return self.getDataSet().to_json(orient='split')