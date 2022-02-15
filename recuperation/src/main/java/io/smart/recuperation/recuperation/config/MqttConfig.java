package io.smart.recuperation.recuperation.config;

import org.eclipse.paho.client.mqttv3.IMqttClient;
import org.eclipse.paho.client.mqttv3.MqttClient;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;


public class MqttConfig {
    private static final String MQTT_BROKER = "test.mosquitto.org";
    private static final String MQTT_PUBLISHER_ID = "spring-server";

    private static final String MQTT_SERVER_ADDRESS= "tcp://test.mosquitto.org:1883";
//    private static final String MQTT_SERVER_ADDRESS= "tcp://192.168.1.69:1883";

    private static IMqttClient instance;

    public static IMqttClient getInstance() {
        try {
            if (instance == null) {
                instance = new MqttClient(MQTT_SERVER_ADDRESS, MQTT_PUBLISHER_ID);
            }

            MqttConnectOptions options = new MqttConnectOptions();
            options.setAutomaticReconnect(true);
            options.setCleanSession(true);
            options.setConnectionTimeout(10);
//            options.setPassword(new char[]{'p', 'a', 's', 's', 'w', 'o', 'r', 'd'});

            if (!instance.isConnected()) {
                instance.connect(options);
            }
        } catch (MqttException e) {
            e.printStackTrace();
        }

        return instance;
    }

    private MqttConfig() {

    }
}