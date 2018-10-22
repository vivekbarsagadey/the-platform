from com.platform.health.diabetes.services.db_handler import DBHandler


class Registration:

    def __init__(self, org={}):
        self.usertype=org['usertype']
        self.fname=org['fname']
        self.lname = org['lname']
        self.email = org['email']
        self.password = org['password']

    def __str__(self):
        return str(self.__dict__)

    def details(self):
        sampleString = {self.__dict__}

        DBHandler().getUserInput().insert_one(sampleString)
