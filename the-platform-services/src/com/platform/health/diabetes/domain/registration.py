from com.platform.health.diabetes.services.db_handler import DBHandler


class Registration:

    def __init__(self, org={}):
        self.usertype=org['usertype']
        self.FirstName=org['FirstName']
        self.LastName = org['LastName']
        self.Email = org['Email']
        self.Date_of_Birth = org['Date_of_Birth']

    def __str__(self):
        return str(self.__dict__)

    def details(self):
        sampleString = {self.__dict__}

        DBHandler().getUserInput().insert_one(sampleString)
