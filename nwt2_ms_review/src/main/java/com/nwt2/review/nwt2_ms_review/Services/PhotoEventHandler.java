package com.nwt2.review.nwt2_ms_review.Services;

import net.minidev.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.core.annotation.HandleAfterCreate;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * Created by ohrinator on 4/12/18.
 */
@Component
public class PhotoEventHandler {

    private final Logger logger = LoggerFactory.getLogger(this.getClass());
    private RabbitTemplate rabbitTemplate;

    private Queue photoReviewCreatedQueue;

    @Autowired
    public PhotoEventHandler(RabbitTemplate rabbitTemplate, Queue photoReviewCreatedQueue) {
        this.rabbitTemplate = rabbitTemplate;
        this.photoReviewCreatedQueue = photoReviewCreatedQueue;
    }

    @HandleAfterCreate
    public void handleAfterCreated(String image, Long id) {
        rabbitTemplate.convertAndSend(
                photoReviewCreatedQueue.getName(), serializeToJson(image, id)
        );

        logger.info("Sent the image to Photo MS");
    }

    private String serializeToJson(String image, Long id) {
        JSONObject imobj = new JSONObject();

        imobj.put("review_id", id);
        imobj.put("image", image);

        return imobj.toString();
    }
}
