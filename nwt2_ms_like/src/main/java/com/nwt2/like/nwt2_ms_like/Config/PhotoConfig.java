package com.nwt2.like.nwt2_ms_like.Config;

import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Created by ohrinator on 4/12/18.
 */
@Configuration
public class PhotoConfig {
    @Bean
    public Queue photoReviewCreatedQueue() {
        return new Queue("photo_review_created_queue");
    }
}
