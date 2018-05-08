package com.nwt2.review.nwt2_ms_review.Services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nwt2.review.nwt2_ms_review.Model.Location;
import com.nwt2.review.nwt2_ms_review.Model.User;
import com.nwt2.review.nwt2_ms_review.Repository.LocationRepository;
import com.nwt2.review.nwt2_ms_review.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
@Service
public class LocationListService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    private LocationRepository locationRepository;


    @RabbitListener(queues = "#{locationCreatedQueue.name}")

    public void getCandidateMessage(String userCreatedMessage) {
        logger.info(userCreatedMessage);
        ObjectMapper objectMapper = new ObjectMapper();
        Location location = null;
        try {
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            location = objectMapper.readValue(userCreatedMessage, Location.class);

        } catch (IOException e) {

            logger.info(String.valueOf(e.getMessage()));

        }

      locationRepository.save(location);
        logger.info(String.valueOf(location.getId()));
        //logger.debug("Location {} saved to DB", location.toString());
    }

    @RabbitListener(queues = "#{locationUpdatedQueue.name}")

    public void getUpdateUserMessage(String userCreatedMessage) {
        logger.info(userCreatedMessage);
        ObjectMapper objectMapper = new ObjectMapper();
        Location location = null;
        try {
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            location = objectMapper.readValue(userCreatedMessage, Location.class);

        } catch (IOException e) {

            logger.info(String.valueOf(e.getMessage()));

        }
        locationRepository.save(location);
    }

    @RabbitListener(queues = "#{locationDeletedQueue.name}")

    public void getDeleteUserMessage(String userCreatedMessage) {
        logger.info(userCreatedMessage);
        ObjectMapper objectMapper = new ObjectMapper();
        Location location = null;
        try {
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            location = objectMapper.readValue(userCreatedMessage, Location.class);

        } catch (IOException e) {

            logger.info(String.valueOf(e.getMessage()));

        }
        locationRepository.delete(location);
    }

}
