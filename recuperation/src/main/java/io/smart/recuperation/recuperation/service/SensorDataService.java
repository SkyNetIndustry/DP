package io.smart.recuperation.recuperation.service;

import io.smart.recuperation.recuperation.domain.SensorData;
import io.smart.recuperation.recuperation.model.SensorDataDTO;
import io.smart.recuperation.recuperation.repos.SensorDataRepository;
import io.smart.recuperation.recuperation.repos.SensorRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import io.smart.recuperation.recuperation.service.transform.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class SensorDataService {

    private final SensorDataRepository sensorDataRepository;
    private final SensorRepository sensorRepository;
    private final Mapper mapper;

    public SensorDataService(final SensorDataRepository sensorDataRepository,
                             final SensorRepository sensorRepository, final Mapper mapper) {
        this.sensorDataRepository = sensorDataRepository;
        this.sensorRepository = sensorRepository;
        this.mapper = mapper;
    }

    public List<SensorDataDTO> findAll() {
        return sensorDataRepository.findAll()
                .stream()
                .map(sensorData -> mapper.mapSensorDataToDTO(sensorData, new SensorDataDTO()))
                .collect(Collectors.toList());
    }

    public SensorDataDTO get(final Integer id) {
        return sensorDataRepository.findById(id)
                .map(sensorData -> mapper.mapSensorDataToDTO(sensorData, new SensorDataDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Integer create(final SensorDataDTO sensorDataDTO) {
        final SensorData sensorData = new SensorData();
        mapper.mapSensorDataToEntity(sensorDataDTO, sensorData, sensorRepository);
        return sensorDataRepository.save(sensorData).getId();
    }

    public void update(final Integer id, final SensorDataDTO sensorDataDTO) {
        final SensorData sensorData = sensorDataRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapper.mapSensorDataToEntity(sensorDataDTO, sensorData, sensorRepository);
        sensorDataRepository.save(sensorData);
    }

    public void delete(final Integer id) {
        sensorDataRepository.deleteById(id);
    }

    public void deleteWithTruncate() {
        sensorDataRepository.truncate();
    }
}
