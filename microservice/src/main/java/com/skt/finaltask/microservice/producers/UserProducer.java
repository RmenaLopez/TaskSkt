package com.skt.finaltask.microservice.producers;

import com.skt.finaltask.commonLibrary.configuration.RabbitConfiguration;
import com.skt.finaltask.commonLibrary.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Random;



@Service
public class UserProducer {

    /*
    Dummy class as of now.
    This class will publish to the management App Though rabbitmq, right now it is publishing to UserListener.java
    in this project.
     */

    private static final Logger log = LoggerFactory.getLogger(UserProducer.class);

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public UserProducer(final RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedDelay = 3000L)
    public void sendMessage() {
        final User user = new User("Goodbye there!", new Random().nextInt(50));
        log.info("Sending message...");
        rabbitTemplate.convertAndSend(RabbitConfiguration.EXCHANGE_NAME,
                RabbitConfiguration.ROUTING_KEY_TO_FRONT, user);
    }
    @Scheduled(fixedDelay = 4000L)
    public void sendMessage2() {
        final User user = new User("Goodbye there!", new Random().nextInt(50));
        log.info("Sending message...");
        rabbitTemplate.convertAndSend(RabbitConfiguration.EXCHANGE_NAME,
                RabbitConfiguration.ROUTING_KEY_TO_DB, user);
    }
}
