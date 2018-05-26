package com.skt.finaltask.microservice;

import com.skt.finaltask.microservice.entity.UserRecord;
import com.skt.finaltask.microservice.entity.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
//@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRecordTest {

    @Autowired
    private UserRepository userRepository;

    @Before
    public void setUp() throws Exception {

        UserRecord userRecord1 = new UserRecord("Alice", 23);
        UserRecord userRecord2 = new UserRecord("Bob", 38);
        //save user, verify has ID value after save
        assertNull(userRecord1.getId());
        assertNull(userRecord2.getId());//null before save
        userRepository.addUser(userRecord1.getName(), userRecord1.getAge());
        userRepository.addUser(userRecord2.getName(), userRecord2.getAge());
    }

    @Test
    public void testFetchData(){
/*
        UserRecord userRecordA = userRepository.findByName("Bob");
        assertNotNull(userRecordA);
        assertEquals(38, userRecordA.getAge());
*/
        List<UserRecord> users = userRepository.getAllUsers();

        assertEquals(users.size(), 2);
    }


}