package com.skt.finaltask.microservice;

import com.skt.finaltask.microservice.entity.UserRecord;
import com.skt.finaltask.microservice.entity.UserRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;
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
        // TODO: 26/05/18 Implement test for stored procedure getAllUsers()
        int count = 0;
        Iterable<UserRecord> users = userRepository.findAll();

        for(UserRecord userRecord : users){
            count++;
        }

        assertEquals(count, 2);
    }


}