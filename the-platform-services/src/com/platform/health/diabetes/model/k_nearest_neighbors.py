from sklearn.metrics import accuracy_score
from sklearn.model_selection import train_test_split
from sklearn.neighbors import KNeighborsClassifier

from com.platform.health.diabetes.domain.computation import Computation
from com.platform.health.diabetes.services.data_handler import DiabetesDataSet


class KNearestNeighborsModel():
    def __init__(self):
        self.dataDataSet = DiabetesDataSet()
        self.data = self.dataDataSet.getDataSet()
        self.knnmodel =  KNeighborsClassifier()
        self.X_train, self.X_test, self.y_train, self.y_test = train_test_split(self.dataDataSet.getX(),
                                                                                self.dataDataSet.getY(),
                                                                                stratify=self.data.Outcome,
                                                                                random_state=0)
        self.train()

    def train(self):
        self.knnmodel.fit(self.X_train, self.y_train)


    def predict(self):
        #print(json.dumps({'columns' : diabetes.columns} , cls=NumpyEncoder))
        self.y_pred = self.knnmodel.predict(self.X_test)
        return Computation("KNN",accuracy_score(self.y_test, self.y_pred)).__dict__
