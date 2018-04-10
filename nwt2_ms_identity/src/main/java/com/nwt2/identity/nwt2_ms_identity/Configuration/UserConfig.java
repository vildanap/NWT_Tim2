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

    public Queue candidateCreatedQueue() {

        return new Queue("candidate_created_queue");

    }


}