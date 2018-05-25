package com.skt.finaltask.microservice.entity;

import org.springframework.stereotype.Component;

import javax.persistence.EntityManager;
import javax.persistence.ParameterMode;
import javax.persistence.PersistenceContext;
import javax.persistence.StoredProcedureQuery;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class UserRepositoryImpl implements UserRepository{

    @PersistenceContext
    private EntityManager entityManager;

    @Override
    public List<UserRecord> getAllUsers() {
        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("find_all_users");

        List<Object[]> storedProcedureResults = storedProcedure.getResultList();

        return storedProcedureResults.stream().map( o -> new UserRecord(
                (String) o[2],
                (int) o[1]
        )).collect(Collectors.toList());
    }

    @Override
    public void addUser(UserRecord userRecord) {
        StoredProcedureQuery storedProcedureQuery = entityManager.createStoredProcedureQuery("insert_new_user");

        storedProcedureQuery.registerStoredProcedureParameter("Name", String.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("Name",userRecord.getName());
        storedProcedureQuery.registerStoredProcedureParameter("Age", Integer.class, ParameterMode.IN);
        storedProcedureQuery.setParameter("Age",userRecord.getAge());

        storedProcedureQuery.execute();
    }

}
