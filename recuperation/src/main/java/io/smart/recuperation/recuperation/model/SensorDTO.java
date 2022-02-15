package io.smart.recuperation.recuperation.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SensorDTO {

    private Integer id;

    private Double value;

    @Size(max = 255)
    private String description;

    private Location location;

    private Integer stair;

}
