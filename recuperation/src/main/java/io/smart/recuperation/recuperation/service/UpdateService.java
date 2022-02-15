package io.smart.recuperation.recuperation.service;

import io.smart.recuperation.recuperation.model.SensorCollectionDTO;
import io.smart.recuperation.recuperation.model.SensorDataDTO;
import io.smart.recuperation.recuperation.service.transform.CollectionCreator;
import io.smart.recuperation.recuperation.service.transform.Json;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class UpdateService {
    private final CollectionCreator collectionCreator;
    private final SensorDataService sensorDataService;
    private final SensorCollectionService sensorCollectionService;

    public UpdateService(final CollectionCreator collectionCreator,
                         final SensorDataService sensorDataService,
                         final SensorCollectionService sensorCollectionService) {
        this.collectionCreator = collectionCreator;
        this.sensorDataService = sensorDataService;
        this.sensorCollectionService = sensorCollectionService;
    }


    // TODO 1. - make new cron (15, 30 min ??)
    // @Scheduled(cron = "0 0 10 * * MON-SUN") //https://www.freeformatter.com/cron-expression-generator-quartz.html
//    @Scheduled(cron = "0 */5 * * * *")
    @Scheduled(fixedRate = 5 * 60 * 1000)
    public void updateCollectionWithSensorData () {
        System.out.println("Update data at: " + LocalDateTime.now());
        List<SensorDataDTO> sensorData = sensorDataService.findAll();

        if (sensorData.isEmpty()) {
            System.out.println("No data to update!!!");
            return;
        }
        HashMap<LocalDate, HashMap<Integer, HashMap<Integer, Double>>> data = collectionCreator.createCollections(sensorData);

        for (LocalDate key_day : data.keySet()) {
            createOrUpdateSensorCollection(data.get(key_day), key_day);
        }
        sensorDataService.deleteWithTruncate();
    }

    private void createOrUpdateSensorCollection (final HashMap<Integer, HashMap<Integer, Double>> values, final LocalDate localDate) {
        String value = Json.collectionListEncoder(values);
        SensorCollectionDTO newSensorCollectionDTO = new SensorCollectionDTO();
        newSensorCollectionDTO.setValue(value);
        newSensorCollectionDTO.setDayDate(localDate);

        try {
            SensorCollectionDTO sensorCollectionDTO = sensorCollectionService.getByDayDate(localDate);
            newSensorCollectionDTO = collectionCreator.updateWithNewData(sensorCollectionDTO, values);
            sensorCollectionService.update(sensorCollectionDTO.getId(), newSensorCollectionDTO);

        } catch (Exception e) {
            System.out.println(e);
            System.out.println("COLLECTION DATE " + localDate + " NOT EXIST! WILL BE CREATE");
            sensorCollectionService.create(newSensorCollectionDTO);
        }
    }
}
