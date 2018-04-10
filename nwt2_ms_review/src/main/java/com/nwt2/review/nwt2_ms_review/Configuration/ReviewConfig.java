package com.nwt2.review.nwt2_ms_review.Configuration;


import org.springframework.amqp.core.DirectExchange;

import org.springframework.amqp.core.Queue;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;



@Configuration

public class ReviewConfig {

    @Bean

    public Queue candidateCreatedQueue() {

        return new Queue("candidate_created_queue");

    }


}