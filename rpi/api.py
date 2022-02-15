import json
from sensor import Sensor
from generator import Generator
from publish import Publisher


sensors = [Sensor(id=i) for i in range(1, 12)]
#def compare_data

def get_data_from_sensors():
    for i, sensor in enumerate(sensors):
        sensors[i] = Generator().getGeneratedSensorValue(sensor)
    return sensors

def sensors_to_json(sensors):
    post = [json.loads(sensor.toJson()) for sensor in sensors]
    return json.dumps(post).translate({ord('['): None}).translate({ord(']'): None})


def run():
    client = Publisher().connect_mqtt()
    client.loop_start()
    
    
    while(1):
        get_data_from_sensors()
        
        json_values = sensors_to_json(sensors)
        
        Publisher().publish(client, json_values, 'python/mqtt')

        
if __name__ == '__main__':
    run()