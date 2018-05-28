package com.skt.finaltask.microservice.repository;

import com.skt.finaltask.commonLibrary.model.User;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;

@Transactional
public interface UserRepository extends CrudRepository<User, Integer>{

    @Procedure( name = "addUser")
        void addUser(@Param("name") String name, @Param("age") Integer age);

    // TODO: 26/05/18 Make this procedure work!!
    @Procedure( procedureName = "getAllUsers")
        List<User> getAllUsers();
}
