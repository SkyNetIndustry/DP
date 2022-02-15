import json

class Sensor:
    def __init__(self, id, value=None, date=None, temperature=20, factor=40, factorstatus=-0.1):
        self.id = id
        self.value = temperature
        self.date = date
        
        self.factorstatus = factorstatus
        self.factor = factor
        self.temperature = temperature
            
    def toJson(self):
        return json.dumps({"id" : self.id, 
                           "value" : self.value})