package io.smart.recuperation.recuperation.rest;

import io.smart.recuperation.recuperation.model.SensorDTO;
import io.smart.recuperation.recuperation.service.SensorService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin
@RequestMapping(value = "/api/sensors", produces = MediaType.APPLICATION_JSON_VALUE)
public class SensorController {

    private final SensorService sensorService;

    public SensorController(final SensorService sensorService) {
        this.sensorService = sensorService;
    }

    @GetMapping
    public ResponseEntity<List<SensorDTO>> getAllSensors() {
        return ResponseEntity.ok(sensorService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<SensorDTO> getSensor(@PathVariable final Integer id) {
        return ResponseEntity.ok(sensorService.get(id));
    }

    @GetMapping("/{stair_id}/sensors")
    public ResponseEntity<List<SensorDTO>> getAllSensors(@PathVariable final Integer stair_id) {
        return ResponseEntity.ok(sensorService.findSensorsInStair(stair_id));
    }

    @PostMapping
    public ResponseEntity<Integer> createSensor(@RequestBody @Valid final SensorDTO sensorDTO) {
        return new ResponseEntity<>(sensorService.create(sensorDTO), HttpStatus.CREATED);
    }

    @PostMapping("/{stair_id}")
    public ResponseEntity<Integer> createSensor(@PathVariable final Integer stair_id) {
        SensorDTO sensorDTO = new SensorDTO();
        sensorDTO.setStair(stair_id);

        return new ResponseEntity<>(sensorService.create(sensorDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSensor(@PathVariable final Integer id,
            @RequestBody @Valid final SensorDTO sensorDTO) {
        sensorService.update(id, sensorDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteSensor(@PathVariable final Integer id) {
        sensorService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
