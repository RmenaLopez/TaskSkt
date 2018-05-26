package com.skt.finaltask.microservice.entity;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface UserRepository extends Repository{

    List<UserRecord> getAllUsers();

    @Procedure("addUser")
        void addUser(@Param("name") String name, @Param("age") Integer age);
}
