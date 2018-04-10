package com.nwt2.review.nwt2_ms_review.Services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nwt2.review.nwt2_ms_review.Model.User;
import com.nwt2.review.nwt2_ms_review.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class UserListService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private UserRepository userRepository;

    /**

     * Consumes a new candidate message, deserializes, and save to MongoDB

     * @param candidateMessage

     */

    @RabbitListener(queues = "#{candidateCreatedQueue.name}")

    public void getCandidateMessage(String userCreatedMessage) {

        ObjectMapper objectMapper = new ObjectMapper();

        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);



        TypeReference<User> mapType = new TypeReference<User>() {};



        User user = null;



        try {

            user = objectMapper.readValue(userCreatedMessage, mapType);

        } catch (IOException e) {

            logger.info(String.valueOf(e));

        }



        userRepository.save(user);

        logger.debug("Candidate {} saved to MongoDB", user.toString());

    }


}
