from keras.models import model_from_json
import os

BASE_FOLDER = os.path.abspath(os.path.dirname(__name__))
NEXT_PATH = "/healthcare/resources/disease/diabetes/model/save/"
FULL_PATH = BASE_FOLDER + NEXT_PATH


class nethandler:
    def __init__(self):
        print("Store Model init")

    def savenet(self,model=None):
        model_json = model.to_json()
        with open(FULL_PATH+"model.json", "w") as json_file:
            json_file.write(model_json)
        # serialize weights to HDF5
        model.save_weights(FULL_PATH + "model.h5")

    def loadnet(self):
        json_file = open(FULL_PATH + 'model.json', 'r')
        loaded_model_json = json_file.read()
        json_file.close()
        loaded_model = model_from_json(loaded_model_json)
        # load weights into new model
        loaded_model.load_weights(FULL_PATH + "model.h5")
        print("Loaded model from disk")

        return loaded_model
