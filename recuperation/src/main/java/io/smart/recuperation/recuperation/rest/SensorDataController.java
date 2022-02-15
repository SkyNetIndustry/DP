package io.smart.recuperation.recuperation.rest;

import io.smart.recuperation.recuperation.model.SensorDataDTO;
import io.smart.recuperation.recuperation.service.SensorDataService;
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
@RequestMapping(value = "/api/sensorDatas", produces = MediaType.APPLICATION_JSON_VALUE)
public class SensorDataController {

    private final SensorDataService sensorDataService;

    public SensorDataController(final SensorDataService sensorDataService) {
        this.sensorDataService = sensorDataService;
    }

    @GetMapping
    public ResponseEntity<List<SensorDataDTO>> getAllSensorDatas() {
        return ResponseEntity.ok(sensorDataService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SensorDataDTO> getSensorData(@PathVariable final Integer id) {
        return ResponseEntity.ok(sensorDataService.get(id));
    }

    @PostMapping
    public ResponseEntity<Integer> createSensorData(
            @RequestBody @Valid final SensorDataDTO sensorDataDTO) {
        return new ResponseEntity<>(sensorDataService.create(sensorDataDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSensorData(@PathVariable final Integer id,
            @RequestBody @Valid final SensorDataDTO sensorDataDTO) {
        sensorDataService.update(id, sensorDataDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSensorData(@PathVariable final Integer id) {
        sensorDataService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
