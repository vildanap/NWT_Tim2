package com.nwt2.like.nwt2_ms_like.Configuration;


import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PhotoConfig {

    @Bean
    public Queue candidateCreatedQueue() {
        return new Queue("candidate_created_queue");
    }
}