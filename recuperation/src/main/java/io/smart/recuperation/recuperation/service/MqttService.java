package io.smart.recuperation.recuperation.service;

import io.smart.recuperation.recuperation.config.MqttConfig;
import io.smart.recuperation.recuperation.model.MqttSubscribeModel;
import io.smart.recuperation.recuperation.model.SensorDTO;
import io.smart.recuperation.recuperation.model.SensorDataDTO;
import io.smart.recuperation.recuperation.service.transform.Json;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@Service
public class MqttService {


    private final SensorService sensorService;
    private final SensorDataService sensorDataService;

    public MqttService(final SensorService sensorService,
                       final SensorDataService sensorDataService) {
        this.sensorService = sensorService;
        this.sensorDataService = sensorDataService;
    }

    @Scheduled(fixedRate = 1000)
    public void mqttListen() throws MqttException, InterruptedException {
        try {
            subscribeMessages("python/mqtt", 1000).forEach(message -> {
                String msg = message.getMessage();
                if (!Objects.equals(msg, "testMessage") && !Objects.equals(msg, "poruka")) {
                    updateData(message);
                }
            });
        }
        catch (Exception e) {
//            System.out.println(e);
        }
    }

    private void updateData(final MqttSubscribeModel message) {
        HashMap<Integer, Double> list = Json.mqttDecoder(message.getMessage());
        list.forEach((index, value) -> {
            SensorDTO sensorDTO = sensorService.get(index);
            sensorDTO.setValue(value);
            sensorService.update(index, sensorDTO);
        });

        SensorDataDTO sensorDataDTO = new SensorDataDTO();
        sensorDataDTO.setValue(message.getMessage());
        sensorDataService.create(sensorDataDTO);
    }

    public static List<MqttSubscribeModel> subscribeMessages(String topic, Integer waitMillis) throws MqttException, InterruptedException {
        List<MqttSubscribeModel> messages = new ArrayList<>();
        CountDownLatch countDownLatch = new CountDownLatch(10);
        MqttConfig.getInstance().subscribeWithResponse(topic, (s, mqttMessage) -> {
            MqttSubscribeModel mqttSubscribeModel = new MqttSubscribeModel();
            mqttSubscribeModel.setId(mqttMessage.getId());
            mqttSubscribeModel.setMessage(new String(mqttMessage.getPayload()));
            mqttSubscribeModel.setQos(mqttMessage.getQos());
            messages.add(mqttSubscribeModel);
            countDownLatch.countDown();
        });

        countDownLatch.await(waitMillis, TimeUnit.MILLISECONDS);

        return messages;
    }
}
