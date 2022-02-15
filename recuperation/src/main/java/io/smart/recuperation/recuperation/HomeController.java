package io.smart.recuperation.recuperation;

import io.smart.recuperation.recuperation.service.SensorCollectionService;
import io.smart.recuperation.recuperation.service.UpdateService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;


@Controller
public class HomeController {
    private final UpdateService updateService;
    private final SensorCollectionService sensorCollectionService;

    public HomeController(final UpdateService updateService, SensorCollectionService sensorCollectionService) {
        this.updateService = updateService;
        this.sensorCollectionService = sensorCollectionService;
    }

    @GetMapping("/")
    @ResponseBody
    public String index() {
        return "Hello World!";
    }

    @GetMapping("/test")
    public String test() {
        updateService.updateCollectionWithSensorData();

        return "Hello from Test!";
    }

}
