package io.smart.recuperation.recuperation.service;

import io.smart.recuperation.recuperation.domain.Stair;
import io.smart.recuperation.recuperation.model.StairDTO;
import io.smart.recuperation.recuperation.repos.StairRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class StairService {

    private final StairRepository stairRepository;

    public StairService(final StairRepository stairRepository) {
        this.stairRepository = stairRepository;
    }

    public List<StairDTO> findAll() {
        return stairRepository.findAll()
                .stream()
                .map(stair -> mapToDTO(stair, new StairDTO()))
                .collect(Collectors.toList());
    }

    public StairDTO get(final Integer id) {
        return stairRepository.findById(id)
                .map(stair -> mapToDTO(stair, new StairDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Integer create(final StairDTO stairDTO) {
        final Stair stair = new Stair();
        mapToEntity(stairDTO, stair);
        return stairRepository.save(stair).getId();
    }

    public void update(final Integer id, final StairDTO stairDTO) {
        final Stair stair = stairRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(stairDTO, stair);
        stairRepository.save(stair);
    }

    public void delete(final Integer id) {
        stairRepository.deleteById(id);
    }

    private StairDTO mapToDTO(final Stair stair, final StairDTO stairDTO) {
        stairDTO.setId(stair.getId());
        stairDTO.setDescription(stair.getDescription());
        return stairDTO;
    }

    private Stair mapToEntity(final StairDTO stairDTO, final Stair stair) {
        stair.setDescription(stairDTO.getDescription());
        return stair;
    }

}
