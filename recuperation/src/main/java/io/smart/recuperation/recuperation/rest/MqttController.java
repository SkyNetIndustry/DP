package io.smart.recuperation.recuperation.rest;

import io.smart.recuperation.recuperation.config.MqttConfig;
import io.smart.recuperation.recuperation.config.MqttException;
import io.smart.recuperation.recuperation.model.MqttPublishModel;
import io.smart.recuperation.recuperation.model.MqttSubscribeModel;
import io.smart.recuperation.recuperation.service.MqttService;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping(value = "/api/mqtt")
public class MqttController {


    public MqttController() {
    }


    @PostMapping("publish")
    public void publishMessage(@RequestBody @Valid MqttPublishModel messagePublishModel,
                               BindingResult bindingResult) throws org.eclipse.paho.client.mqttv3.MqttException {
        if (bindingResult.hasErrors()) {
            throw new MqttException(MqttException.SOME_PARAMETERS_INVALID);
        }

        MqttMessage mqttMessage = new MqttMessage(messagePublishModel.getMessage().getBytes());
        mqttMessage.setQos(messagePublishModel.getQos());
        mqttMessage.setRetained(messagePublishModel.getRetained());

        MqttConfig.getInstance().publish(messagePublishModel.getTopic(), mqttMessage);
    }

    @GetMapping("subscribe")
    public List<MqttSubscribeModel> subscribeChannel(@RequestParam(value = "topic") String topic,
                                                     @RequestParam(value = "wait_millis") Integer waitMillis)
            throws org.eclipse.paho.client.mqttv3.MqttException, InterruptedException {

        return MqttService.subscribeMessages(topic, waitMillis);
    }
}