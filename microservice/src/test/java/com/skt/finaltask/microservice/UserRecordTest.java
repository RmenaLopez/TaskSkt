package com.skt.finaltask.microservice;

import com.skt.finaltask.microservice.entity.UserRecord;
import com.skt.finaltask.microservice.entity.UserRepository;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Profile;
import org.springframework.data.jpa.repository.query.Procedure;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.*;
@ActiveProfiles("test")
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserRecordTest {

    @Autowired
    UserRepository userRepository;

    @Before
    public void setUp() throws Exception {

        UserRecord userRecord1 = new UserRecord("Un pablito o algo asi", 253);
        UserRecord userRecord2 = new UserRecord("Bobi mac dowg", 738);
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
        int count = 0;
        Iterable<UserRecord> users = userRepository.findAll();

        for(UserRecord userRecord : users){
            count++;
        }

        assertEquals(count, 2);
    }


}