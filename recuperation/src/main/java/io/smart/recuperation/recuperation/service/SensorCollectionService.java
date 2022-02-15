package io.smart.recuperation.recuperation.service;

import io.smart.recuperation.recuperation.domain.SensorCollection;
import io.smart.recuperation.recuperation.model.SensorCollectionDTO;
import io.smart.recuperation.recuperation.repos.SensorCollectionRepository;
import io.smart.recuperation.recuperation.repos.SensorRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

import io.smart.recuperation.recuperation.service.transform.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class SensorCollectionService {

    private final SensorCollectionRepository sensorCollectionRepository;
    private final SensorRepository sensorRepository;
    private final Mapper mapper;

    public SensorCollectionService(final SensorCollectionRepository sensorCollectionRepository,
                                   final SensorRepository sensorRepository, final Mapper mapper) {
        this.sensorCollectionRepository = sensorCollectionRepository;
        this.sensorRepository = sensorRepository;
        this.mapper = mapper;
    }

    public List<SensorCollectionDTO> findAll() {
        return sensorCollectionRepository.findAll()
                .stream()
                .map(sensorCollection -> mapper.mapSensorCollectionToDTO(sensorCollection, new SensorCollectionDTO()))
                .collect(Collectors.toList());
    }

    public SensorCollectionDTO getByDayDate (LocalDate localDate) {
        SensorCollection sensorCollection = new SensorCollection();
        try {
            sensorCollection = sensorCollectionRepository.findByDayDate(localDate);
        } catch (Exception e) {
            System.out.println(e + " " + new ResponseStatusException(HttpStatus.NOT_FOUND));
        }
        return mapper.mapSensorCollectionToDTO(sensorCollection, new SensorCollectionDTO());
       }

    public SensorCollectionDTO get(final Integer id) {
        return sensorCollectionRepository.findById(id)
                .map(sensorCollection -> mapper.mapSensorCollectionToDTO(sensorCollection, new SensorCollectionDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Integer create(final SensorCollectionDTO sensorCollectionDTO) {
        final SensorCollection sensorCollection = new SensorCollection();
        mapper.mapSensorCollectionToEntity(sensorCollectionDTO, sensorCollection, sensorRepository);
        return sensorCollectionRepository.save(sensorCollection).getId();
    }

    public void update(final Integer id, final SensorCollectionDTO sensorCollectionDTO) {
        final SensorCollection sensorCollection = sensorCollectionRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapper.mapSensorCollectionToEntity(sensorCollectionDTO, sensorCollection, sensorRepository);
        sensorCollectionRepository.save(sensorCollection);
    }

    public void delete(final Integer id) {
        sensorCollectionRepository.deleteById(id);
    }
}
