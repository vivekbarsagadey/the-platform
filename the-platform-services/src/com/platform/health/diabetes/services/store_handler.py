import  os
import pickle
import sys
from pathlib import Path

BASE_FLODER = os.path.abspath(os.path.dirname(__name__))
NEXT_PATH="/com/platform/health/diabetes/store/"
FULL_PATH = BASE_FLODER + NEXT_PATH
file_ext = '.sav'

class StoreHandle:
    def __init__(self):
        print("Store Model init")

    def getModel(self, name="all_model"):
        config = Path(FULL_PATH+name+file_ext)
        Models = pickle.load(open(FULL_PATH+name+file_ext, 'rb'))
        return Models

    def saveModel(self , name="all_model", model=None):
        pickle.dump(model, open(FULL_PATH+name+file_ext, 'wb'))
