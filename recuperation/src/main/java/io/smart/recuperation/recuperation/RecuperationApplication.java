package io.smart.recuperation.recuperation;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;


@EnableAsync
@SpringBootApplication
@EnableScheduling
public class RecuperationApplication {

    public static void main(String[] args) {
        SpringApplication.run(RecuperationApplication.class, args);
    }

}
// https://bootify.io/