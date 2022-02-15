package io.smart.recuperation.recuperation.service.transform;

import io.smart.recuperation.recuperation.domain.Sensor;
import io.smart.recuperation.recuperation.domain.SensorCollection;
import io.smart.recuperation.recuperation.domain.SensorData;
import io.smart.recuperation.recuperation.domain.Stair;
import io.smart.recuperation.recuperation.model.SensorCollectionDTO;
import io.smart.recuperation.recuperation.model.SensorDTO;
import io.smart.recuperation.recuperation.model.SensorDataDTO;
import io.smart.recuperation.recuperation.repos.SensorRepository;
import io.smart.recuperation.recuperation.repos.StairRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.time.LocalDateTime;

@Component
public class Mapper {

    public SensorDTO mapSensorToDTO(final Sensor sensor, final SensorDTO sensorDTO) {
        sensorDTO.setId(sensor.getId());
        sensorDTO.setValue(sensor.getValue());
        sensorDTO.setDescription(sensor.getDescription());
        sensorDTO.setLocation(sensor.getLocation());
        sensorDTO.setStair(sensor.getStair() == null ? null : sensor.getStair().getId());
        return sensorDTO;
    }

    public Sensor mapSensorStairToEntity(final SensorDTO sensorDTO, final Sensor sensor, final StairRepository stairRepository) {
        sensor.setValue(sensorDTO.getValue());
        sensor.setDescription(sensorDTO.getDescription());
        sensor.setLocation(sensorDTO.getLocation());
        if (sensorDTO.getStair() != null && (sensor.getStair() == null || !sensor.getStair().getId().equals(sensorDTO.getStair()))) {
            final Stair stair = stairRepository.findById(sensorDTO.getStair())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "stair not found"));
            sensor.setStair(stair);
        }
        return sensor;
    }

    public SensorDataDTO mapSensorDataToDTO(final SensorData sensorData, final SensorDataDTO sensorDataDTO) {
        sensorDataDTO.setId(sensorData.getId());
        sensorDataDTO.setDate(sensorData.getDate());
        sensorDataDTO.setValue(sensorData.getValue());
        return sensorDataDTO;
    }

    public SensorData mapSensorDataToEntity(final SensorDataDTO sensorDataDTO, final SensorData sensorData, final SensorRepository sensorRepository) {
        if (sensorDataDTO.getDate() == null) {
            sensorData.setDate(LocalDateTime.now());
        } else {
            sensorData.setDate(sensorDataDTO.getDate());
        }
        sensorData.setValue(sensorDataDTO.getValue());
        return sensorData;
    }

    public SensorCollectionDTO mapSensorCollectionToDTO(final SensorCollection sensorCollection,
                                         final SensorCollectionDTO sensorCollectionDTO) {
        sensorCollectionDTO.setId(sensorCollection.getId());
        sensorCollectionDTO.setValue(sensorCollection.getValue());
        sensorCollectionDTO.setDayDate(sensorCollection.getDayDate());
        return sensorCollectionDTO;
    }

    public SensorCollection mapSensorCollectionToEntity(final SensorCollectionDTO sensorCollectionDTO,
                                         final SensorCollection sensorCollection, final SensorRepository sensorRepository) {
        sensorCollection.setValue(sensorCollectionDTO.getValue());
        sensorCollection.setDayDate(sensorCollectionDTO.getDayDate());
        return sensorCollection;
    }
}
