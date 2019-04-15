from sklearn.ensemble import GradientBoostingClassifier
from sklearn.ensemble import RandomForestClassifier
from sklearn.linear_model import LogisticRegression
from sklearn.metrics import accuracy_score
from sklearn.model_selection import train_test_split
from sklearn.naive_bayes import GaussianNB
from sklearn.neighbors import KNeighborsClassifier
from sklearn.svm import SVC
from sklearn.tree import DecisionTreeClassifier
from healthcare.resources.disease.diabetes.model.computation import Computation
from healthcare.resources.disease.diabetes.dataset.data_handler import DiabetesDataSet
from healthcare.resources.disease.diabetes.model.model_handler import StoreHandle

models = [('KNeighborsClassifier', KNeighborsClassifier()), ('SVC', SVC()),
          ('LogisticRegression', LogisticRegression()), ('DecisionTreeClassifier', DecisionTreeClassifier()),
          ('GaussianNB', GaussianNB()), ('RandomForestClassifier', RandomForestClassifier()),
          ('GradientBoostingClassifier', GradientBoostingClassifier())]


class AllModels:

    def __init__(self):
        self.dataDataSet = DiabetesDataSet()
        self.data = self.dataDataSet.getdataset
        self.X_train, self.X_test, self.y_train, self.y_test = train_test_split(self.dataDataSet.getx,
                                                                                self.dataDataSet.gety,
                                                                                stratify=self.data.Outcome,
                                                                                random_state=0)

    def train(self):

        for name, model in models:
            model.fit(self.dataDataSet.getx, self.dataDataSet.gety)

        StoreHandle().saveModel(name="all_model", model=models)

    def loadmodels(self):
        return StoreHandle().getModel(name="all_model")

    def predictAll(self):
        names = []
        scores = []
        computationList = []
        for name, model in self.loadmodels():
            self.y_pred = model.predict(self.X_test)
            scores.append(accuracy_score(self.y_test, self.y_pred))
            names.append(name)
            computationList.append(Computation(name, accuracy_score(self.y_test, self.y_pred)).__dict__)
        return computationList


    def predict(self, x={}):
        computationList = []
        print("x", x)
        for name, model in self.loadmodels():
            y_pred = model.predict(self.dataDataSet.datasetvalue(x))
            print("y_pred", y_pred)
            computationList.append(Computation(name, pred=str(y_pred[0])).__dict__)
        return computationList
