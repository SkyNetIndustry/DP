package io.smart.recuperation.recuperation.rest;

import io.smart.recuperation.recuperation.model.SensorCollectionDTO;
import io.smart.recuperation.recuperation.service.SensorCollectionService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/sensorCollections", produces = MediaType.APPLICATION_JSON_VALUE)
public class SensorCollectionController {

    private final SensorCollectionService sensorCollectionService;

    public SensorCollectionController(final SensorCollectionService sensorCollectionService) {
        this.sensorCollectionService = sensorCollectionService;
    }

    @GetMapping
    public ResponseEntity<List<SensorCollectionDTO>> getAllSensorCollections() {
        return ResponseEntity.ok(sensorCollectionService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SensorCollectionDTO> getSensorCollection(@PathVariable final Integer id) {
        return ResponseEntity.ok(sensorCollectionService.get(id));
    }

    @PostMapping
    public ResponseEntity<Integer> createSensorCollection(
            @RequestBody @Valid final SensorCollectionDTO sensorCollectionDTO) {
        return new ResponseEntity<>(sensorCollectionService.create(sensorCollectionDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSensorCollection(@PathVariable final Integer id,
            @RequestBody @Valid final SensorCollectionDTO sensorCollectionDTO) {
        sensorCollectionService.update(id, sensorCollectionDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSensorCollection(@PathVariable final Integer id) {
        sensorCollectionService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
