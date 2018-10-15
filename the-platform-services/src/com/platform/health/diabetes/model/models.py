"""
Save model in to disk
"""
from sklearn.ensemble import GradientBoostingClassifier
from sklearn.ensemble import RandomForestClassifier
from sklearn.linear_model import LogisticRegression
from sklearn.metrics import accuracy_score
from sklearn.model_selection import train_test_split
from sklearn.naive_bayes import GaussianNB
from sklearn.neighbors import KNeighborsClassifier
from sklearn.svm import SVC
from sklearn.tree import DecisionTreeClassifier
from collections import ChainMap
from bson.json_util import dumps
import json
from com.platform.health.diabetes.domain.computation import Computation
from com.platform.health.diabetes.services.data_handler import DiabetesDataSet
from com.platform.health.diabetes.services.store_handler import StoreHandle
from com.platform.health.diabetes.services.db_handler import DBHandler

models = []

models.append(('KNeighborsClassifier', KNeighborsClassifier()))
models.append(('SVC', SVC()))
models.append(('LogisticRegression', LogisticRegression()))
models.append(('DecisionTreeClassifier', DecisionTreeClassifier()))
models.append(('GaussianNB', GaussianNB()))
models.append(('RandomForestClassifier', RandomForestClassifier()))
models.append(('GradientBoostingClassifier', GradientBoostingClassifier()))

class AllModels():

    def __init__(self):
        self.dataDataSet = DiabetesDataSet()
        self.data = self.dataDataSet.getDataSet()
        self.X_train, self.X_test, self.y_train, self.y_test = train_test_split(self.dataDataSet.getX(), self.dataDataSet.getY(),
                                                            stratify=self.data.Outcome, random_state=0)


    def train(self):
        names = []
        scores = []
        for name, model in models:
            model.fit(self.dataDataSet.getX(), self.dataDataSet.getY())

        StoreHandle().saveModel(name="all_model",model=models)



    def loadModels (self):
        return StoreHandle().getModel(name="all_model")

    def predictAll(self):
        #print(json.dumps({'columns' : diabetes.columns} , cls=NumpyEncoder))
        names = []
        scores = []
        computationList = []
        for name, model in self.loadModels():
            self.y_pred = model.predict(self.X_test)
            scores.append(accuracy_score(self.y_test, self.y_pred))
            names.append(name)
            computationList.append(Computation(name,accuracy_score(self.y_test, self.y_pred)).__dict__)

        #tr_split = pd.DataFrame({'Name': names, 'Score': scores})
        #print(tr_split)
        #print(tr_split.values)
        return computationList
        #return tr_split.to_json(orient='split')


    def predict(self , x = {}):
        #print(json.dumps({'columns' : diabetes.columns} , cls=NumpyEncoder))
        names = []
        scores = []
        computationList = []
        print("x",x)
        computationData = {}
        for name, model in self.loadModels():
            y_pred = model.predict( self.dataDataSet.getXByDataFrame(x) )
            print("y_pred", y_pred)

            computationList.append(Computation(name,pred = str(y_pred[0])).__dict__)
            computationData[name] =str(y_pred[0])
        print(computationData)
        DBHandler().storeRecordResult().insert(computationData)
        return computationList
