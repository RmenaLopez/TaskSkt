package com.skt.finaltask.microservice;

import com.skt.finaltask.commonLibrary.model.User;
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
public class UserTest {

    @Autowired
    UserRepository userRepository;

    @Before
    public void setUp() throws Exception {

        User user1 = new User("Un pablito o algo asi", 253);
        User user2 = new User("Bobi mac dowg", 738);
        //save user, verify has ID value after save
        assertNull(user1.getId());
        assertNull(user2.getId());//null before save
        userRepository.addUser(user1.getName(), user1.getAge());
        userRepository.addUser(user2.getName(), user2.getAge());
    }

    @Test
    public void testFetchData(){
        // TODO: 26/05/18 Implement test for stored procedure getAllUsers()
        int count = 0;

        Iterable<User> users = userRepository.findAll();

        for(User user : users){
            count++;
        }

        assertEquals(count, 2);
    }


}