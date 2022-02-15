package io.smart.recuperation.recuperation.config;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class MqttException extends RuntimeException{
    public static final String SOME_PARAMETERS_INVALID = "SOME_PARAMETERS_INVALID";

    public MqttException(String message) {
        super(message);
    }
}
