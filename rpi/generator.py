import random, datetime
from sensor import Sensor

class Generator:
    def getDates(numbers):
        print_time = []
        for i in range(0, numbers):
            next_time = datetime.datetime.today() + datetime.timedelta(minutes = i)
            print_time.append(datetime.datetime.strftime(next_time , '%d.%m - %H:%M'))
        return print_time
    
    @staticmethod
    def getNextTempAndFactor(temperature, factor, factorstatus):
        n = round(random.random() * 100)
            
        if 15 <= n <= 85:
            return [temperature, factor, factorstatus]
    
        if n > factor:
            temperature += 0.01
            if n > round(random.random() * 100):
                factor += factorstatus
        else:
            temperature -= 0.01
            if n > round(random.random() * 100):
                factor += factorstatus
        
    
        if temperature > 22:
            temperature -= 0.05
            factor += 1
        
        if temperature < 18:
            temperature += 0.05
            factor -= 1
        
        #if i > 4000:
         #   weather = 'winter'
          #  factorstatus *= -1
          
        return [round(temperature, 2), round(factor, 2), factorstatus]
    
    def getGeneratedSensorValue(self, sensor):
        temperature = 0
        factor = 0
        factorstatus = 0
                
        [temperature, factor, factorstatus] = self.getNextTempAndFactor(sensor.temperature, sensor.factor, sensor.factorstatus)
        return Sensor(sensor.id, temperature=temperature, factor=factor, factorstatus=factorstatus)