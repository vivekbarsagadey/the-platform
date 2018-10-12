import os
from flask import Flask, request, redirect, url_for, send_from_directory, render_template
import time
from datetime import date
from werkzeug.utils import secure_filename

import mimetypes

ALLOWED_EXTENSIONS = set(['csv', 'xlsx', 'xls', 'txt'])

UPLOAD_FOLDER = os.path.abspath(os.path.dirname(__name__))
UPLOAD_FOLDER_PATH="/com/platform/health/diabetes/data/upload/"

app = Flask(__name__)
app.config['UPLOAD_FOLDER'] = UPLOAD_FOLDER+UPLOAD_FOLDER_PATH

def allowed_file(filename):
    return '.' in filename and \
           filename.rsplit('.', 1)[1] in ALLOWED_EXTENSIONS


class FileHandler:
    def __init__(self):
        print("File handler init")

    def saveFile(self, request):
        print("File uploading is started")
        if 'file' not in request.files:
            print("No file found")
            return "No file found"

        file = request.files['file']
        completeFileName = ""
        if file and allowed_file(file.filename):
            filename = secure_filename(file.filename)
            #today = date().today().isoformat(timespec='microseconds')
            completeFileName=(os.path.join(app.config['UPLOAD_FOLDER'],filename))
            print("filename >>>>>>>>>>>> ",completeFileName)
            file.save(completeFileName)

        return completeFileName

