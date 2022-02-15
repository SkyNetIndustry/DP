package io.smart.recuperation.recuperation.repos;

import io.smart.recuperation.recuperation.domain.SensorData;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;


public interface SensorDataRepository extends JpaRepository<SensorData, Integer> {

    @Query(value = "TRUNCATE TABLE sensor_data", nativeQuery = true)
    @Modifying
    @Transactional
    void truncate();
}
