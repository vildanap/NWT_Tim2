package com.nwt2.review.nwt2_ms_review.Services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nwt2.review.nwt2_ms_review.Model.User;
import com.nwt2.review.nwt2_ms_review.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.io.IOException;


@Service
public class UserListService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private UserRepository userRepository;


    @RabbitListener(queues = "#{userCreatedQueue.name}")

    public void getCreateUserMessage(String userCreatedMessage) {
        logger.info(userCreatedMessage);
        ObjectMapper objectMapper = new ObjectMapper();
        User user = null;

        try {
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            user = objectMapper.readValue(userCreatedMessage, User.class);

        } catch (IOException e) {

            logger.info(String.valueOf(e.getMessage()));

        }

     userRepository.save(user);
     logger.debug("User {} saved to DB", user.toString());

    }

    @RabbitListener(queues = "#{userUpdatedQueue.name}")

    public void getUpdateUserMessage(String userCreatedMessage) {
        logger.info(userCreatedMessage);
        ObjectMapper objectMapper = new ObjectMapper();
        User user = null;

        try {
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            user = objectMapper.readValue(userCreatedMessage, User.class);

        } catch (IOException e) {

            logger.info(String.valueOf(e.getMessage()));

        }
        userRepository.save(user);
        logger.debug("User {} saved to DB", user.toString());

    }

    @RabbitListener(queues = "#{userDeletedQueue.name}")

    public void getDeleteUserMessage(String userCreatedMessage) {
        logger.info(userCreatedMessage);
        ObjectMapper objectMapper = new ObjectMapper();
        User user = null;

        try {
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            user = objectMapper.readValue(userCreatedMessage, User.class);

        } catch (IOException e) {

            logger.info(String.valueOf(e.getMessage()));

        }
        userRepository.delete(user);
        logger.debug("User {} deleted from DB", user.toString());

    }

}
