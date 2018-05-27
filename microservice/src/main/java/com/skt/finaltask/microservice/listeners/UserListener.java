package com.skt.finaltask.microservice.listeners;

import com.skt.finaltask.commonLibrary.configuration.RabbitConfiguration;
import com.skt.finaltask.commonLibrary.model.User;
import com.skt.finaltask.microservice.entity.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserListener {
    private static final Logger log = LoggerFactory.getLogger(UserListener.class);

    @Autowired
    private UserRepository repository;

    @RabbitListener(queues = RabbitConfiguration.QUEUE_USER_TO_DB)
    public void receiveUserToDB(final User user) {
        log.info("Received User TO DB: {}", user.toString());

        User userRecord = new User();
        userRecord.setAge(user.getAge());
        userRecord.setName(user.getName());

        repository.addUser(userRecord.getName(), userRecord.getAge());
    }
}
