package io.smart.recuperation.recuperation.rest;

import io.smart.recuperation.recuperation.model.StairDTO;
import io.smart.recuperation.recuperation.service.StairService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@CrossOrigin //https://stackoverflow.com/questions/32319396/cors-with-spring-boot-and-angularjs-not-working
@RequestMapping(value = "/api/stairs", produces = MediaType.APPLICATION_JSON_VALUE)
public class StairController {

    private final StairService stairService;

    public StairController(final StairService stairService) {
        this.stairService = stairService;
    }

    @GetMapping
    public ResponseEntity<List<StairDTO>> getAllStairs() {
        return ResponseEntity.ok(stairService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<StairDTO> getStair(@PathVariable final Integer id) {
        return ResponseEntity.ok(stairService.get(id));
    }

    @PostMapping
    public ResponseEntity<Integer> createStair(@RequestBody @Valid final StairDTO stairDTO) {
        return new ResponseEntity<>(stairService.create(stairDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateStair(@PathVariable final Integer id,
            @RequestBody @Valid final StairDTO stairDTO) {
        stairService.update(id, stairDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteStair(@PathVariable final Integer id) {
        stairService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
