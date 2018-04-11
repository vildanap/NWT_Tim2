package com.nwt2.identity.nwt2_ms_identity.Configuration;




import com.nwt2.identity.nwt2_ms_identity.Services.UserEventHandler;
import org.springframework.amqp.core.DirectExchange;

import org.springframework.amqp.core.Exchange;
import org.springframework.amqp.core.Queue;

import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.context.annotation.Bean;

import org.springframework.context.annotation.Configuration;


@Configuration
public class UserConfig {

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


}