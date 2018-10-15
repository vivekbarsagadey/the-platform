import csv
import re
import os
import glob
from com.platform.health.diabetes.parser.patient_features import PatientFeatures
from com.platform.health.diabetes.model.models import AllModels

UPLOAD_FOLDER = os.path.abspath(os.path.dirname(__name__))
UPLOAD_FOLDER_PATH="/com/platform/health/diabetes/data/upload/"
Final_Path = UPLOAD_FOLDER+UPLOAD_FOLDER_PATH
extension = 'csv'
fieldname = ('patientId','pregnancy','glucose','bloodpressure','skinThickness','insulin','bmi','diabetesPedigreeFunction','age')

word_regex_pattern = re.compile("[^A-Za-z]+")
def camel(chars):
  words = word_regex_pattern.split(chars)
  return "".join(w.lower() if i is 0 else w.title() for i, w in enumerate(words))


class PatientDataParser():
    def __init__(self):
        print("the parser method is called.")

    def getPatientListFromFile(self):
        print("patient features loaded")
        os.chdir(Final_Path)
        file = [i for i in glob.glob('*.{}'.format(extension))]
        for csvfile in file:
            name = open(os.path.join(Final_Path, csvfile), 'r')
            reader = csv.DictReader(name,fieldname)
            next(reader)
            for row in reader:
                #print("new row",row)
                print("Storing the Features in Database")
                patientFeatures = PatientFeatures(org=row)
                userDataFrame = patientFeatures.getFrame()
                #print("age >>> ",patientFeatures.age)
                patientFeatures.saveFeatures()
                print("Features saved to database sucessfully.")
                AllModels().predict(userDataFrame)
                print("the prediction method is called.")
            name.close()
            os.remove(csvfile)
            print("The locally stored file is deleted")
        return True