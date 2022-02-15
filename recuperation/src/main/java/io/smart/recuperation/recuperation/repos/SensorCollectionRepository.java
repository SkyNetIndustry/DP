package io.smart.recuperation.recuperation.repos;

import io.smart.recuperation.recuperation.domain.SensorCollection;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.Optional;


public interface SensorCollectionRepository extends JpaRepository<SensorCollection, Integer> {
    SensorCollection findByDayDate (LocalDate localDate);
}
