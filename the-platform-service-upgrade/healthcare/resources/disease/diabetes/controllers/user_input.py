from flask_restplus import fields
from healthcare import api

user_fields = api.model('Prediction', {
    'firstName': fields.String,
    'lastName': fields.String(),
    'pregnancy': fields.Integer(),
    'glucose': fields.Integer(),
    'bloodpressure': fields.Integer(),
    'skinThickness': fields.Integer(),
    'insulin': fields.Integer(),
    'bmi': fields.Float(),
    'diabetesPedigreeFunction': fields.Float(),
    'age': fields.Integer()
})
