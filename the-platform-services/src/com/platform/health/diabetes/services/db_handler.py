import pymongo
from pymongo import MongoClient

connection = MongoClient()
dbs = connection.the_platfrom_db

class DBHandler:
    def __init__(self):
        print("DBHandler init")
        print(connection.list_database_names())
    def getDataSource(self):
       return (dbs)

    def getUserDataSource(self):
        return dbs.users

    def getPatientDataSource(self):
        return dbs.patients