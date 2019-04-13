import pandas as pd
import os

BASE_DIR = os.path.abspath(os.path.dirname(__name__))
NEXT_FOLDER_PATH = "/healthcare/resources/disease/diabetes/data/"
FULL_PATH = BASE_DIR + NEXT_FOLDER_PATH

df = pd.read_csv(os.path.join(FULL_PATH, 'diabetes.csv'))
feature_names = ['Pregnancies', 'Glucose', 'BloodPressure', 'SkinThickness', 'Insulin', 'BMI',
                 'DiabetesPedigreeFunction', 'Age']


class DiabetesDataSet():

    def getDataSet(self):
        return df

    def getx(self):
        return self.getDataSet()[feature_names]

    def getXByDataFrame(self, df):
        return df[feature_names]

    def gety(self):
        return self.getDataSet().Outcome
