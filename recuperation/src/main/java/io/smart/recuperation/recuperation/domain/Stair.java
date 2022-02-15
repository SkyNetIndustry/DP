package io.smart.recuperation.recuperation.domain;

import java.util.Set;
import javax.persistence.*;

import lombok.Getter;
import lombok.Setter;


@Entity
@Getter
@Setter
public class Stair {

    @Id
    @Column(nullable = false, updatable = false)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "\"description\"")
    private String description;

    @OneToMany(mappedBy = "stair", fetch = FetchType.LAZY)
    private Set<Sensor> stairSensors;

}
