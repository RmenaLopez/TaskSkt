package com.skt.finaltask.microservice.entity;

import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<UserRecord, Integer> {
    UserRecord findByName(String name);

}
