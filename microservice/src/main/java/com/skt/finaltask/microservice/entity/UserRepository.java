package com.skt.finaltask.microservice.entity;

import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface UserRepository extends CrudRepository<UserRecord, Integer>{

    @Procedure( name = "addUser")
        void addUser(@Param("name") String name, @Param("age") Integer age);

    // TODO: 26/05/18 Make this procedure work!!
    @Procedure( procedureName = "getAllUsers")
        List<UserRecord> getAllUsers();
}
