package com.nwt2.identity.nwt2_ms_identity.Services;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;

import com.nwt2.identity.nwt2_ms_identity.Model.Role;
import com.nwt2.identity.nwt2_ms_identity.Model.User;

import com.nwt2.identity.nwt2_ms_identity.Repository.RoleRepository;
import com.nwt2.identity.nwt2_ms_identity.Repository.UserRepository;
import org.slf4j.Logger;

import org.slf4j.LoggerFactory;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;

import org.springframework.amqp.rabbit.core.RabbitTemplate;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.data.rest.core.annotation.HandleAfterCreate;

import org.springframework.data.rest.core.annotation.RepositoryEventHandler;

import org.springframework.stereotype.Component;

@Component

@RepositoryEventHandler

public class UserEventHandler {


    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    private RabbitTemplate rabbitTemplate;

    private Queue candidateCreatedQueue;



    @Autowired

    public UserEventHandler(RabbitTemplate rabbitTemplate, Queue userQueue) {

        this.rabbitTemplate = rabbitTemplate;

        this.candidateCreatedQueue = userQueue;

    }


    @HandleAfterCreate

    public void handleUserSave(User user) {

        sendMessage(user);

    }



    private void sendMessage(User user) {

        rabbitTemplate.convertAndSend(

                candidateCreatedQueue.getName(), serializeToJson(user));

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