package io.smart.recuperation.recuperation.model;

import java.time.LocalDate;
import java.time.LocalDateTime;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class SensorDataDTO {

    private Integer id;

    @NotNull
    private LocalDateTime date;

    @NotNull
    private String value;

}
