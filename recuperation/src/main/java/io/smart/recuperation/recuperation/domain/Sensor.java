package io.smart.recuperation.recuperation.domain;

import javax.persistence.*;

import io.smart.recuperation.recuperation.model.Location;
import lombok.Getter;
import lombok.Setter;

import java.util.Set;


@Entity
@Getter
@Setter
public class Sensor {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column
    private Double value;

    @Column(name = "\"description\"")
    private String description;

    @Column
    @Enumerated(EnumType.STRING)
    private Location location;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "stair_id", nullable = false)
    private Stair stair;
}
