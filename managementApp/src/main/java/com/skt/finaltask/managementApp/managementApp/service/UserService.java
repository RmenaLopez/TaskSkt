package com.skt.finaltask.managementApp.managementApp.service;

import com.skt.finaltask.commonLibrary.configuration.RabbitConfiguration;
import com.skt.finaltask.commonLibrary.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final RabbitTemplate rabbitTemplate;
    private List<User> userList;

    @Autowired
    public UserService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    public void addUser(User user) {
        log.info("Sending user to db...");
        rabbitTemplate.convertAndSend(RabbitConfiguration.EXCHANGE_NAME,
                RabbitConfiguration.ROUTING_KEY_TO_DB_USERS, user);
    }

    @RabbitListener(queues = RabbitConfiguration.QUEUE_USERS_TO_FRONT)
    private void getUsersFromDB(final List<User> users){
        this.userList = users;
    }

    public List<User> getUsers(){
        return userList;
    }

}
