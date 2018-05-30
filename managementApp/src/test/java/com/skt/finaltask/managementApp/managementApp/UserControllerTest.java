package com.skt.finaltask.managementApp.managementApp;

import com.skt.finaltask.commonLibrary.model.User;
import com.skt.finaltask.managementApp.managementApp.controller.UserController;
import com.skt.finaltask.managementApp.managementApp.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(UserController.class)
public class UserControllerTest {

    @MockBean
    private UserService userService;

    @InjectMocks
    private UserController userController;
 
    @Autowired
    private MockMvc mvc;

    @Before
    public void setup(){
        MockitoAnnotations.initMocks(this);

        this.mvc = MockMvcBuilders.standaloneSetup(userController).build();
    }


    @Test
    public void addCorrectUser() throws Exception{

        User user = new User("Rafael mena", 23);

        mvc.perform(post("/new-user")
        .param("name", user.getName())
        .param("age", Integer.toString(user.getAge()))
        .flashAttr("user",new User())).andExpect(status().isCreated());

    }

    @Test
    public void addInvalidUser() throws Exception{

        User invalidNameUser = new User("", 23);
        User invalidAgeUser = new User("Rafael mena", -1);
        User invalidUser = new User("", 0);

        mvc.perform(post("/new-user")
        .param("name", invalidAgeUser.getName())
        .param("age", Integer.toString(invalidAgeUser.getAge()))
        .flashAttr("user",new User())).andExpect(status().isBadRequest());

        mvc.perform(post("/new-user")
        .param("name", invalidNameUser.getName())
        .param("age", Integer.toString(invalidNameUser.getAge()))
        .flashAttr("user",new User())).andExpect(status().isBadRequest());

        mvc.perform(post("/new-user")
        .param("name", invalidUser.getName())
        .param("age", Integer.toString(invalidUser.getAge()))
        .flashAttr("user",new User())).andExpect(status().isBadRequest());
    }
}
