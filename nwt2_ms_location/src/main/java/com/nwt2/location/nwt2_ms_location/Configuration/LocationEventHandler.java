package com.nwt2.location.nwt2_ms_location.Configuration;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nwt2.location.nwt2_ms_location.Model.Location;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

@Component
@RepositoryEventHandler
public class LocationEventHandler {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private RabbitTemplate rabbitTemplate;

    private Queue locationCreatedQueue;



    @Autowired

    public LocationEventHandler(RabbitTemplate rabbitTemplate, Queue locationQueue) {

        this.rabbitTemplate = rabbitTemplate;

        this.locationCreatedQueue = locationQueue;

    }


    @HandleAfterSave
    public void handleLocationSave(Location location) {

        sendMessage(location);
        logger.info("Kreirana lokacija", location);

    }



    private void sendMessage(Location location) {

        rabbitTemplate.convertAndSend(

                locationCreatedQueue.getName(), serializeToJson(location));

    }



    private String serializeToJson(Location location) {

        ObjectMapper mapper = new ObjectMapper();

        String jsonInString = "";



        try {

            jsonInString = mapper.writeValueAsString(location);

        } catch (JsonProcessingException e) {

            logger.info(String.valueOf(e));

        }


        logger.debug("Serialized message payload: {}", jsonInString);



        return jsonInString;

    }
}
