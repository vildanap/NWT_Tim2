package com.nwt2.like.nwt2_ms_like.Services;


import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nwt2.like.nwt2_ms_like.Model.Photo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.stereotype.Component;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import org.springframework.amqp.core.Queue;

@Component

@RepositoryEventHandler
public class PhotoEventHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private RabbitTemplate rabbitTemplate;

    private Queue candidateCreatedQueue;



    @Autowired
    public PhotoEventHandler(RabbitTemplate rabbitTemplate, Queue photoQueue) {
        this.rabbitTemplate = rabbitTemplate;
        this.candidateCreatedQueue = photoQueue;
    }


    @HandleAfterCreate
    public void handlePhotoSave(Photo photo) {
        sendMessage(photo);
    }



    private void sendMessage(Photo photo) {
        rabbitTemplate.convertAndSend(
                candidateCreatedQueue.getName(), serializeToJson(photo));
    }



    private String serializeToJson(Photo photo) {

        ObjectMapper mapper = new ObjectMapper();
        String jsonInString = "";

        try {
            jsonInString = mapper.writeValueAsString(photo);
        } catch (JsonProcessingException e) {
            logger.info(String.valueOf(e));
        }


        logger.debug("Serialized message payload: {}", jsonInString);

        return jsonInString;
    }



}