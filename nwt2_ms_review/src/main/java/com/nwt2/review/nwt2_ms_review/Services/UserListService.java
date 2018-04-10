package com.nwt2.review.nwt2_ms_review.Services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.nwt2.review.nwt2_ms_review.Model.User;
import com.nwt2.review.nwt2_ms_review.Repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.support.converter.DefaultClassMapper;
import org.springframework.amqp.support.converter.Jackson2JsonMessageConverter;
import org.springframework.amqp.support.converter.MessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class UserListService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private UserRepository userRepository;

    /**

     * Consumes a new candidate message, deserializes, and save to MongoDB

     * @param candidateMessage

     */

    @RabbitListener(queues = "#{candidateCreatedQueue.name}")

    public void getCandidateMessage(String userCreatedMessage) {
        logger.info(userCreatedMessage);
        ObjectMapper objectMapper = new ObjectMapper();

        //TypeReference<User> mapType = new TypeReference<User>() {};
        User user = null;
        //user.setId();
        try {
            objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);
            user = objectMapper.readValue(userCreatedMessage, User.class);

            //user = objectMapper.readValue(userCreatedMessage, mapType);

        } catch (IOException e) {

            logger.info(String.valueOf(e.getMessage()));

        }



    userRepository.save(user);
    logger.debug("Candidate {} saved to MongoDB", user.toString());



    }

    @Bean
    public MessageConverter jsonConverter() {
        Jackson2JsonMessageConverter converter = new Jackson2JsonMessageConverter();
        DefaultClassMapper mapper = new DefaultClassMapper();
        mapper.setDefaultType(User.class);
        converter.setClassMapper(mapper);
        return converter;
    }


}
