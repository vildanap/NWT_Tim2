package com.nwt2.review.nwt2_ms_review.Configuration;


import org.springframework.amqp.core.DirectExchange;

import org.springframework.amqp.core.Queue;

import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;



@Configuration

public class ReviewConfig {

    @Bean

    public Queue userCreatedQueue() {

        return new Queue("user_created_queue");

    }

    @Bean

    public Queue userUpdatedQueue() {

        return new Queue("user_updated_queue");

    }

    @Bean

    public Queue userDeletedQueue() {

        return new Queue("user_deleted_queue");

    }
    @Bean

    public Queue locationCreatedQueue() {

        return new Queue("location_created_queue");

    }
    @Bean

    public Queue locationUpdatedQueue() {

        return new Queue("location_updated_queue");

    }

    @Bean

    public Queue locationDeletedQueue() {

        return new Queue("location_deleted_queue");

    }



}