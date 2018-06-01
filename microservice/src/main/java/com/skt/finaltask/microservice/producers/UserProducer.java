package com.skt.finaltask.microservice.producers;

import com.skt.finaltask.commonLibrary.configuration.RabbitConfiguration;
import com.skt.finaltask.commonLibrary.model.Product;
import com.skt.finaltask.commonLibrary.model.User;
import com.skt.finaltask.microservice.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class UserProducer {

    private UserRepository repository;

    private static final Logger log = LoggerFactory.getLogger(UserProducer.class);

    private final RabbitTemplate rabbitTemplate;

    @Autowired
    public UserProducer(final RabbitTemplate rabbitTemplate, UserRepository userRepository) {
        this.rabbitTemplate = rabbitTemplate;
        this.repository = userRepository;
    }

    @Scheduled(fixedDelay = 3000L)
    public void sendMessageToFront() {
        log.info("Sending user list...");

        rabbitTemplate.convertAndSend(RabbitConfiguration.EXCHANGE_NAME,
                RabbitConfiguration.ROUTING_KEY_TO_FRONT_USERS,
                repository.getAllUsers());
    }
}
