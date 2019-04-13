import os
import pickle
from pathlib import Path

BASE_FOLDER = os.path.abspath(os.path.dirname(__name__))
NEXT_PATH = "/healthcare/resources/disease/diabetes/model/save/"
FULL_PATH = BASE_FOLDER + NEXT_PATH
file_ext = '.sav'


class StoreHandle:
    def __init__(self):
        print("Store Model init")

    def getModel(self, name="all_model"):
        config = Path(FULL_PATH + name + file_ext)
        Models = pickle.load(open(FULL_PATH + name + file_ext, 'rb'))
        return Models

    def saveModel(self, name="all_model", model=None):
        pickle.dump(model, open(FULL_PATH + name + file_ext, 'wb'))
