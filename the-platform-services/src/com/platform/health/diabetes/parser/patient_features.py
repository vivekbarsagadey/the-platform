
import json

import pandas as pd

from com.platform.health.diabetes.services.db_handler import DBHandler

class PatientFeatures:
    def __init__(self ,org={}):
        self.patient_id = org['patientId']
        self.pregnancy = org['pregnancy']
        self.glucose = org['glucose']
        self.bloodpressure = org['bloodpressure']
        self.skinThickness = org['skinThickness']
        self.insulin = org['insulin']
        self.bmi = org['bmi']
        self.diabetesPedigreeFunction = org['diabetesPedigreeFunction']
        self.age = org['age']
        self.outcome=0

    def __str__(self):
        return str(self.__dict__)

    def saveFeatures(self):
        user_String = json.dumps(self.__dict__)
        print("User json is ")
        print(user_String)
        DBHandler().getPatientDataSource().insert_one(json.loads(user_String))



    def getFrame(self ):
        return pd.DataFrame(
            {'Pregnancies': self.pregnancy, 'Glucose': self.glucose, 'BloodPressure': self.bloodpressure, 'SkinThickness': self.skinThickness,
             'Insulin': self.insulin, 'BMI': self.bmi,
             'DiabetesPedigreeFunction': self.diabetesPedigreeFunction, 'Age': self.age}, index=[0])

