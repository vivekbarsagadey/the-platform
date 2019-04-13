import pandas as pd
import os

BASE_DIR = os.path.abspath(os.path.dirname(__name__))
NEXT_FOLDER_PATH = "/healthcare/resources/disease/diabetes/dataset/files/"
FULL_PATH = BASE_DIR + NEXT_FOLDER_PATH

df = pd.read_csv(os.path.join(FULL_PATH, 'diabetes.csv'))
feature_names = ['Pregnancies', 'Glucose', 'BloodPressure', 'SkinThickness', 'Insulin', 'BMI',
                 'DiabetesPedigreeFunction', 'Age']


class DiabetesDataSet:

    @property
    def getdataset(self):
        return df

    @property
    def getx(self):
        return self.getdataset[feature_names]

    @staticmethod
    def datasetvalue(df):
        return df[feature_names]

    @property
    def gety(self):
        return self.getdataset.Outcome
