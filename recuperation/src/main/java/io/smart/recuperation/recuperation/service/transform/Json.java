package io.smart.recuperation.recuperation.service.transform;

import org.json.JSONObject;
import java.util.*;

public class Json {
    public static HashMap<Integer, Double> mqttDecoder(final String json) {
        String[] sensors_split = json.split("}, ");
        HashMap<Integer, Double> sensors = new HashMap<>();

        for (String sensor : sensors_split) {
            JSONObject jsonToParse = new JSONObject(sensor + "}");
            sensors.put(jsonToParse.getInt("id"), jsonToParse.getDouble("value"));
        }
        return sensors;
    }

    public static HashMap<Integer, Double> sensorDecoder (final String json) {
        String[] sensors_json = json.replace(",", "}}{{").split("}\\{");
        HashMap<Integer, Double> sensors = new HashMap<>();

        for (String sensor_json : sensors_json) {
            JSONObject sensor = new JSONObject(sensor_json);
            sensors.put(Integer.valueOf(sensor.keys().next()), Double.valueOf(sensor.get(sensor.keys().next()).toString()));
        }

        return sensors;
    }

    public static HashMap<Integer, HashMap<Integer, Double>> collectionDecoder (final String dataCollectionValue) {
        HashMap<Integer, HashMap<Integer, Double>> data = new HashMap<>();
        JSONObject jsonObject = new JSONObject(dataCollectionValue);
        for (String key : jsonObject.keySet()) {
            data.put(Integer.valueOf(key), sensorDecoder(jsonObject.get(key).toString()));
        }
        return data;
    }

    public static String collectionListEncoder (final HashMap<Integer, HashMap<Integer, Double>> data) {
        JSONObject json = new JSONObject();
        for (Integer key : data.keySet()) {
            JSONObject values = new JSONObject();
            for (Integer sensor: data.get(key).keySet()) {
                values.put(String.valueOf(sensor), data.get(key).get(sensor));
            }
            json.put(String.valueOf(key), values);
        }
        return json.toString();
    }
}
//https://stackoverflow.com/questions/20117148/how-to-create-json-object-using-string
