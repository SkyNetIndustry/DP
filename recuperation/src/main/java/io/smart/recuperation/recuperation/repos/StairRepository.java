package io.smart.recuperation.recuperation.repos;

import io.smart.recuperation.recuperation.domain.Sensor;
import io.smart.recuperation.recuperation.domain.Stair;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface StairRepository extends JpaRepository<Stair, Integer> {
}
