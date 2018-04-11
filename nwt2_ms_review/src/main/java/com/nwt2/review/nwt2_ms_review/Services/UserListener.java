package com.nwt2.review.nwt2_ms_review.Services;

import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageListener;

/**
 * Created by ohrinator on 4/11/18.
 */
public class UserListener implements MessageListener {

    @Override
    public void onMessage(Message message) {
        System.out.println(new String(message.getBody()));
    }
}
