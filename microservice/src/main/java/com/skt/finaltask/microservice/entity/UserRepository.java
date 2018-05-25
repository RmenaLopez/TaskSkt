package com.skt.finaltask.microservice.entity;

import java.util.List;

public interface UserRepository {

    List<UserRecord> getAllUsers();

    void addUser(UserRecord userRecord);

}
