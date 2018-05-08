package com.nwt2.identity.nwt2_ms_identity.Services;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.nwt2.identity.nwt2_ms_identity.Model.Role;
import com.nwt2.identity.nwt2_ms_identity.Model.User;

import com.nwt2.identity.nwt2_ms_identity.Repository.RoleRepository;
import com.nwt2.identity.nwt2_ms_identity.Repository.UserRepository;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import org.springframework.amqp.core.Queue;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.rest.core.annotation.HandleAfterCreate;

import org.springframework.data.rest.core.annotation.HandleAfterDelete;
import org.springframework.data.rest.core.annotation.HandleAfterSave;
import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import org.springframework.stereotype.Component;

@Component
@RepositoryEventHandler
public class UserEventHandler {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private RabbitTemplate rabbitTemplate;

    private Queue userCreatedQueue;

    private Queue userUpdatedQueue;

    private Queue userDeletedQueue;


    @Autowired

    public UserEventHandler(RabbitTemplate rabbitTemplate, Queue userCreatedQueue, Queue userUpdatedQueue, Queue userDeletedQueue ) {

        this.rabbitTemplate = rabbitTemplate;
        this.userCreatedQueue = userCreatedQueue;
        this.userUpdatedQueue = userUpdatedQueue;
        this.userDeletedQueue = userDeletedQueue;

    }

    @HandleAfterCreate
    public void handleAfterCreated(User user) {

        rabbitTemplate.convertAndSend(

                userCreatedQueue.getName(), serializeToJson(user));
        logger.info("User created", user);
    }

    @HandleAfterSave
    public void handleUserSave(User user) {

        rabbitTemplate.convertAndSend(

                userUpdatedQueue.getName(), serializeToJson(user));
        logger.info("Updated user", user);

    }

    @HandleAfterDelete
    public void handleAfterDeleted(User user) {

        rabbitTemplate.convertAndSend(

                userDeletedQueue.getName(), serializeToJson(user));
        logger.info("Deleted user", user);

    }


    private void sendMessage(User user) {

        rabbitTemplate.convertAndSend(

                userCreatedQueue.getName(), serializeToJson(user));

    }

    private String serializeToJson(User user) {

        ObjectMapper mapper = new ObjectMapper();

        String jsonInString = "";



        try {

            jsonInString = mapper.writeValueAsString(user);

        } catch (JsonProcessingException e) {

            logger.info(String.valueOf(e));

        }


        logger.debug("Serialized message payload: {}", jsonInString);



        return jsonInString;

    }



}