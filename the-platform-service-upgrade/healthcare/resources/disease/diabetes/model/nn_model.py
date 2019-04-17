from healthcare.resources.disease.diabetes.dataset.data_handler import DiabetesDataSet
from healthcare.resources.disease.diabetes.model.net_handler import nethandler
from healthcare.resources.disease.diabetes.model.computation import Computation
from sklearn.preprocessing import StandardScaler
from sklearn.model_selection import train_test_split
from tensorflow.python.keras import Sequential, layers


class neuralNet:
    def __init__(self):
        self.dataset = DiabetesDataSet()
        self.X = self.dataset.getx
        self.y = self.dataset.gety
        self.sc = StandardScaler()
        self.X = self.sc.fit_transform(self.X)
        self.X_train, self.X_test, self.y_train, self.y_test = train_test_split(self.X, self.y, test_size=0.2)

    def trainnet(self):
        classifier = Sequential([
            layers.Dense(4, activation='relu', kernel_initializer='random_normal', input_dim=8),
            layers.Dense(4, activation='relu', kernel_initializer='random_normal'),
            layers.Dense(1, activation='sigmoid', kernel_initializer='random_normal')
        ])
        # Compiling the neural network
        classifier.compile(optimizer='adam', loss='binary_crossentropy', metrics=['accuracy'])
        # Fitting the data to the training dataset
        classifier.fit(self.X_train, self.y_train, batch_size=10, epochs=100)
        eval_model = classifier.evaluate(self.X_train, self.y_train)
        print(eval_model)

        nethandler().savenet(model=classifier)

    def netpredict(self, x={}):
        computationList = []
        get_model = nethandler().loadnet()
        y_pred = get_model.predict(self.dataset.datasetvalue(x))
        print(y_pred)
        if y_pred[0] >= 0.5:
            y_pred = 1
            computationList.append(Computation('Neural net', pred=str(y_pred)).__dict__)
            return computationList
        else:
            y_pred = 0
            computationList.append(Computation('Neural net', pred=str(y_pred)).__dict__)
            return computationList
