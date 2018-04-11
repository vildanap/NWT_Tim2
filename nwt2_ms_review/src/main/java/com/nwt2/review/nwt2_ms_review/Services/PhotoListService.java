package com.nwt2.review.nwt2_ms_review.Services;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.nwt2.review.nwt2_ms_review.Model.Photo;
import com.nwt2.review.nwt2_ms_review.Repository.PhotoRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
public class PhotoListService {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private PhotoRepository photoRepository;

    @RabbitListener(queues = "#{candidateCreatedQueue.name}")

    public void getCandidateMessage(String photoCreatedMessage) {

        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);

        TypeReference<Photo> mapType = new TypeReference<Photo>() {};
        Photo photo = null;

        try {

            photo = objectMapper.readValue(photoCreatedMessage, mapType);

        } catch (IOException e) {

            logger.info(String.valueOf(e));

        }



        photoRepository.save(photo);

        logger.debug("Candidate {} saved to MongoDB", photo.toString());

    }
}
