package io.smart.recuperation.recuperation.model;

import javax.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class StairDTO {

    private Integer id;

    @Size(max = 255)
    private String description;

}
