import os
from flask import Flask, request
from flask_restplus import Resource, reqparse
from com.platform.health.diabetes.services.file_handler import  FileHandler
from com.platform.health.diabetes.parser import patient_data_parser


class UserFileuploadscontroller(Resource):
    def get(self):
        return ''

    def post(self):
        print("UserFileuploadscontroller post called")
        FileHandler().saveFile(request)
        patient_data_parser.PatientDataParser().getPatientListFromFile()
        return "file successfully saved"