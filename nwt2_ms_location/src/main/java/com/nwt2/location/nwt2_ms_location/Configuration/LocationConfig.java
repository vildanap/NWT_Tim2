package com.nwt2.location.nwt2_ms_location.Configuration;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.amqp.core.Queue;


@Configuration

public class LocationConfig {
    @Bean
    public Queue locaitonCreatedQueue() {

        return new Queue("location_created_queue");

    }

}
