package com.tb.web;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.tb.domain.User;
import com.tb.service.UserService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by Tivadar Bocz on 2017.01.31..
 */
@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class, secure = false)
public class UserControllerUnitTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserService userService;

    @Before
    public void init() {
        /*MockitoAnnotations.initMocks(this);
        mockMvc = MockMvcBuilders
                .standaloneSetup(userController)
                .addFilters(new CORSFilter())
                .build();*/
    }


    /**
     * localhost:8080/user GET
     */
    @Test
    public void findAllUser() throws Exception {
        List<User> users = new ArrayList<>();
        users.add(new User("Bela", new BCryptPasswordEncoder().encode("user"), false));
        users.add(new User("Irena", new BCryptPasswordEncoder().encode("user"), false));

        Mockito.when(userService.finAll()).thenReturn(users);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user").accept(MediaType.APPLICATION_JSON);
        //Make a call a service
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk()) // HTTP STATUS CODE 200
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();


        ObjectMapper mapper = new ObjectMapper();
        String expedtedUsersJSON = mapper.writeValueAsString(users);

        JSONAssert.assertEquals(expedtedUsersJSON, mvcResult.getResponse().getContentAsString(), false);
        //Mockito.verifyNoMoreInteractions(userService);
    }

    /**
     * localhost:8080/user/1 GET
     */
    @Test
    public void findUserById() throws Exception {
        User user = new User("Bela", new BCryptPasswordEncoder().encode("user"), false);
        Mockito.when(userService.findById(1L)).thenReturn(user);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/user/{id}", 1).accept(MediaType.APPLICATION_JSON);
        //Make a call a service
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk()) // HTTP STATUS CODE 200
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();


        ObjectMapper mapper = new ObjectMapper();
        String expedtedUsersJSON = mapper.writeValueAsString(user);

        JSONAssert.assertEquals(expedtedUsersJSON, mvcResult.getResponse().getContentAsString(), false);
    }

    /**
     * localhost:8080/user POST
     */
    @Test
    public void createUser() throws Exception {
        User user = new User("Bela", "user");
        Mockito.when(userService.createUser(Mockito.any())).thenReturn(user);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.post("/user")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"bela\", \"password\":\"bela\"}");

        //Make a call a service
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk()) // HTTP STATUS CODE 200
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();


        ObjectMapper mapper = new ObjectMapper();
        String expectedUsersJSON = mapper.writeValueAsString(user);

        JSONAssert.assertEquals(expectedUsersJSON, mvcResult.getResponse().getContentAsString(), false);
    }

    /**
     * localhost:8080/user PUT
     */
    @Test
    public void updateUser() throws Exception {
        User user = new User("Bela", "user");
        Mockito.when(userService.updateUser(Mockito.any())).thenReturn(user);

        RequestBuilder requestBuilder = MockMvcRequestBuilders.put("/user")
                .accept(MediaType.APPLICATION_JSON)
                .contentType(MediaType.APPLICATION_JSON)
                .content("{\"username\":\"bela\", \"password\":\"bela\"}");

        //Make a call a service
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().isOk()) // HTTP STATUS CODE 200
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8_VALUE))
                .andReturn();


        ObjectMapper mapper = new ObjectMapper();
        String expectedUsersJSON = mapper.writeValueAsString(user);

        JSONAssert.assertEquals(expectedUsersJSON, mvcResult.getResponse().getContentAsString(), false);
    }

    /**
     * localhost:8080/user/{id} DELETE
     */
    @Test
    public void deleteUser() throws Exception {

        RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/user/{id}", 1);
        //Make a call a service
        MvcResult mvcResult = mockMvc.perform(requestBuilder)
                .andExpect(status().isNotFound()) // HTTP STATUS CODE 404
                .andReturn();

    }


}
