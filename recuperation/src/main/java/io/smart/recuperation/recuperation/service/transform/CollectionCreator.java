package io.smart.recuperation.recuperation.service.transform;

import io.smart.recuperation.recuperation.model.SensorCollectionDTO;
import io.smart.recuperation.recuperation.model.SensorDataDTO;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Component;

import java.text.DecimalFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

@AllArgsConstructor
@Component
public class CollectionCreator {
    /**
     *
     * @param sensorDataDTOList
     * HashMap<Integer, List<HashMap<Integer, HashMap<Integer, Double>>>>
     *
     *     HashMap<Integer, Double>                                           -> <Sensor Id, Sensor Value> (sensors)
     *
     *     HashMap<Integer, HashMap<Integer, Double>>                         -> <Hour, sensors> (hours = one day)
     *
     *     HashMap<Integer, List<HashMap<Integer, HashMap<Integer, Double>>>> -> <day, hours>
     *
     *
     *
     * @return
     */

    public HashMap<LocalDate, HashMap<Integer, HashMap<Integer, Double>>> createCollections(final List<SensorDataDTO> sensorDataDTOList) {
        HashMap<LocalDate, HashMap<Integer, HashMap<Integer, Double>>> collections = new HashMap<>();
        List<SensorDataDTO> tmpSensorDataDTOList = new ArrayList<>();

        for (SensorDataDTO sensorDataDTO : sensorDataDTOList) {
            if (!tmpSensorDataDTOList.isEmpty()) {
                int lastDay = tmpSensorDataDTOList.get(tmpSensorDataDTOList.size() - 1).getDate().getDayOfYear();
                if (sensorDataDTO.getDate().getDayOfYear() != lastDay) {
                    collections.put(tmpSensorDataDTOList.get(tmpSensorDataDTOList.size() - 1).getDate().toLocalDate(), createOneDayCollection(tmpSensorDataDTOList));
                    tmpSensorDataDTOList = new ArrayList<>();
                }
            }
            tmpSensorDataDTOList.add(sensorDataDTO);
        }

        collections.put(tmpSensorDataDTOList.get(tmpSensorDataDTOList.size() - 1).getDate().toLocalDate(), createOneDayCollection(tmpSensorDataDTOList));
        return collections;
    }

    public SensorCollectionDTO updateWithNewData(SensorCollectionDTO sensorCollectionDTO, final HashMap<Integer, HashMap<Integer, Double>> new_data) {
        HashMap<Integer, HashMap<Integer, Double>> old_data = Json.collectionDecoder(sensorCollectionDTO.getValue());
        for (Integer hour : new_data.keySet()) {
            if (!old_data.containsKey(hour)) {
                old_data.put(hour, new_data.get(hour));
            }
            if (!new_data.get(hour).equals(old_data.get(hour))) {
                DecimalFormat f = new DecimalFormat("##.00");
                old_data.get(hour).replaceAll((key, value) ->
                        Double.valueOf(f.format((
                                value + new_data.get(hour).get(key)) / 2)));
            }
        }
        sensorCollectionDTO.setValue(Json.collectionListEncoder(old_data));
        return sensorCollectionDTO;
    }

    private HashMap<Integer, HashMap<Integer, Double>> createOneDayCollection (final List<SensorDataDTO> sensorDataDTOList) {
        HashMap<Integer, HashMap<Integer, Double>> hoursCollection = new HashMap<>();
        List<SensorDataDTO> tmpSensorDataDTOList = new ArrayList<>();
        int lastHour = 0;

        for (SensorDataDTO sensorDataDTO : sensorDataDTOList) {
            if (!tmpSensorDataDTOList.isEmpty()) {
                lastHour = tmpSensorDataDTOList.get(tmpSensorDataDTOList.size() - 1).getDate().getHour();
                if (sensorDataDTO.getDate().getHour() != lastHour) {
                    hoursCollection.put(lastHour, createOneHourCollection(tmpSensorDataDTOList));
                    tmpSensorDataDTOList = new ArrayList<>();
                }
            }
            tmpSensorDataDTOList.add(sensorDataDTO);
        }

        hoursCollection.put(lastHour, createOneHourCollection(tmpSensorDataDTOList));
        return hoursCollection;
    }

    private HashMap<Integer, Double> createOneHourCollection (final  List<SensorDataDTO> sensorDataDTOList) {
        HashMap<Integer, Double> sensors = new HashMap<>();
        DecimalFormat f = new DecimalFormat("##.00");

        for (int index = 1; index < 13; index++) {
            sensors.put(index, 0.0);
        }

        for (SensorDataDTO sensorDataDTO : sensorDataDTOList) {
            HashMap<Integer, Double> values = Json.mqttDecoder(sensorDataDTO.getValue());
            values.forEach((index, value) -> sensors.replace(index, sensors.get(index) + value));
        }

        for (int i = 1; i < 13; i++) {
            Double value = Double.valueOf(f.format(sensors.get(i)/ sensorDataDTOList.size()));
            sensors.replace(i, value);
        }
        return sensors;
    }
}
