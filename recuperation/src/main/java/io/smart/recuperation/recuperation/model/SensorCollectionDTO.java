package io.smart.recuperation.recuperation.model;

import java.time.LocalDate;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SensorCollectionDTO {

    private Integer id;

    @NotNull
    private String value;

    @NotNull
    private LocalDate dayDate;
}
