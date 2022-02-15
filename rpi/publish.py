import time
from paho.mqtt import client as mqtt_client


class Publisher:
    
    def __init__(self, broker = 'test.mosquitto.org', port = 1883, client_id = 'rpi'):
        self.broker = broker
        self.port = port
        self.client_id = client_id
    
    def connect_mqtt(self):
        def on_connect(client, userdata, flags, rc):
            if rc == 0:
                print("Connected to MQTT Broker!")
            else:
                print("Failed to connect, return code %d\n", rc)

        client = mqtt_client.Client(self.client_id)
        #client.username_pw_set(username, password)
        client.on_connect = on_connect
        client.connect(self.broker, self.port)
        return client
    
    def publish(self, client, msg, topic):
        time.sleep(1)
        result = client.publish(topic, msg)
        status = result[0]
        
        if status == 0:
            print(msg)
        else:
            print("Failed to send message to topic")