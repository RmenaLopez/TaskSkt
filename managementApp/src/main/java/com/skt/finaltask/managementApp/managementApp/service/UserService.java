package com.skt.finaltask.managementApp.managementApp.service;

import com.skt.finaltask.commonLibrary.configuration.RabbitConfiguration;
import com.skt.finaltask.commonLibrary.model.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Service
public class UserService {

    private static final Logger log = LoggerFactory.getLogger(UserService.class);

    private final RabbitTemplate rabbitTemplate;
    private List<User> userList;

    @Autowired
    public UserService(RabbitTemplate rabbitTemplate) {
        this.rabbitTemplate = rabbitTemplate;
    }

    @Scheduled(fixedDelay = 3000L)
    public void addUser() {
        final User user = new User("Pablito perez", new Random().nextInt(50));
        log.info("Sending message...");
        rabbitTemplate.convertAndSend(RabbitConfiguration.EXCHANGE_NAME,
                RabbitConfiguration.ROUTING_KEY_TO_DB, user);
    }

    @RabbitListener(queues = RabbitConfiguration.QUEUE_USER_TO_FRONT)
    private void getUsersFromDB(final List<User> users){
        List<User> userList = new ArrayList<>();
        for (User user : users){
            userList.add(new User(user.getName(), user.getAge()));
            System.out.println(user.toString());
        }
        this.userList = userList;
    }

    public List<User> getUsers(){
        return userList;
    }

}
