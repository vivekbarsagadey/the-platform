import os
from flask import Flask, request
from flask_restful import Resource, reqparse
from com.platform.health.diabetes.services.file_handler import  FileHandler

class UserFileuploadscontroller(Resource):
    def get(self):
        return ''

    def post(self):
        print("UserFileuploadscontroller post called")
        FileHandler().save(request)
        return "file successfully saved"