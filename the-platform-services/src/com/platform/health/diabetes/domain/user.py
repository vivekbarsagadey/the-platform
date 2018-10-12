import json

import pandas as pd

from com.platform.health.diabetes.services.db_handler import DBHandler


class User:
    def __init__(self ,org={}):
        self.firstName = org['firstName']
        self.lastName = org['lastName']
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

    def details(self):
        sample_String = {'pregnancy':self.pregnancy,'glucose':self.glucose,'bloodpressure':self.bloodpressure,
                         'skinThickness':self.skinThickness,'insulin':self.insulin,'bmi':self.bmi,
                         'diabetesPedigreeFunction':self.diabetesPedigreeFunction,'age':self.age}
        sample_String.update(patientObjectId = self.objectId)

        DBHandler().getPatientMedicalRecord().insert_one(sample_String)

    def save(self):

        user_String = {'firstName': self.firstName, 'lastName': self.lastName}
        print("Sampling_String:",user_String)
        value = DBHandler().getUserDataSource().insert_one(user_String)
        self.objectId= value.inserted_id
        User.details(self)

    def getFrame(self ):

        return pd.DataFrame(
            {'Pregnancies': self.pregnancy, 'Glucose': self.glucose, 'BloodPressure': self.bloodpressure, 'SkinThickness': self.skinThickness,
             'Insulin': self.insulin, 'BMI': self.bmi,
             'DiabetesPedigreeFunction': self.diabetesPedigreeFunction, 'Age': self.age}, index=[0])

