package io.smart.recuperation.recuperation.service;

import io.smart.recuperation.recuperation.domain.Sensor;
import io.smart.recuperation.recuperation.model.SensorDTO;
import io.smart.recuperation.recuperation.repos.SensorRepository;
import io.smart.recuperation.recuperation.repos.StairRepository;
import java.util.List;
import java.util.stream.Collectors;

import io.smart.recuperation.recuperation.service.transform.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class SensorService {

    private final SensorRepository sensorRepository;
    private final StairRepository stairRepository;
    private final Mapper mapper;

    public SensorService(final SensorRepository sensorRepository,
                         final StairRepository stairRepository, final Mapper mapper) {
        this.sensorRepository = sensorRepository;
        this.stairRepository = stairRepository;
        this.mapper = mapper;
    }

    public List<SensorDTO> findAll() {
        return sensorRepository.findAll()
                .stream()
                .map(sensor -> mapper.mapSensorToDTO(sensor, new SensorDTO()))
                .collect(Collectors.toList());
    }

    public List<SensorDTO> findSensorsInStair (final Integer id) {
        return sensorRepository.findSensorsByStair_Id(id)
                .stream()
                .map(sensor -> mapper.mapSensorToDTO(sensor, new SensorDTO()))
                .collect(Collectors.toList());
    }

    public SensorDTO get(final Integer id) {
        return sensorRepository.findById(id)
                .map(sensor -> mapper.mapSensorToDTO(sensor, new SensorDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Integer create(final SensorDTO sensorDTO) {
        final Sensor sensor = new Sensor();
        mapper.mapSensorStairToEntity(sensorDTO, sensor, stairRepository);
        return sensorRepository.save(sensor).getId();
    }

    public void update(final Integer id, final SensorDTO sensorDTO) {
        final Sensor sensor = sensorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapper.mapSensorStairToEntity(sensorDTO, sensor, stairRepository);
        sensorRepository.save(sensor);
    }

    public void delete(final Integer id) {
        sensorRepository.deleteById(id);
    }
}
