package io.smart.recuperation.recuperation.repos;

import io.smart.recuperation.recuperation.domain.Sensor;
import io.smart.recuperation.recuperation.domain.Stair;
import io.smart.recuperation.recuperation.model.SensorDTO;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface SensorRepository extends JpaRepository<Sensor, Integer> {
    List<Sensor> findSensorsByStair_Id(Integer stair_id);
}
