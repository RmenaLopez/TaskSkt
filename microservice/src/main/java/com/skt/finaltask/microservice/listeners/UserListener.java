package com.skt.finaltask.microservice.listeners;

import com.skt.finaltask.commonLibrary.configuration.RabbitConfiguration;
import com.skt.finaltask.commonLibrary.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

@Service
public class UserListener {
    private static final Logger log = LoggerFactory.getLogger(UserListener.class);

    @RabbitListener(queues = RabbitConfiguration.QUEUE_USER_TO_FRONT)
    public void receiveUserToFront(final User user) {
        log.info("Received User TO FRONT: {}", user.toString());
    }

    @RabbitListener(queues = RabbitConfiguration.QUEUE_USER_TO_DB)
    public void receiveUserToDB(final User user) {
        log.info("Received User TO DB: {}", user.toString());
    }
}
