package com.example.demo.controller;

import com.example.demo.model.User;
import com.example.demo.serviceImpl.UserServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.ObjectWriter;
import com.fasterxml.jackson.databind.SerializationFeature;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@WebMvcTest(UserController.class)
public class UserControllerTest {
    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private UserServiceImpl userServiceImpl;
    User userOne;
    User userTwo;
    List<User> userList= new ArrayList<>();

    @BeforeEach
    void setUp() {
        userOne = new User(1,"password","meravalireddy@gmail.com","Rava","S",1234567890);
        userTwo = new User(2,"password","ravalireddy@gmail.com","Dha","m",1238797890);
        userList.add(userOne);
        userList.add(userTwo);
    }

    @AfterEach
    void tearDown() {

    }

    @Test
    void getAllUsers() throws Exception {
        when(userServiceImpl.getAllUsers()).thenReturn(userList);
        this.mockMvc.perform(get("/api/users"))
                .andDo(print()).andExpect(status().isOk());
    }
    @Test
    void getUserById() throws Exception {
        when(userServiceImpl.getUserById(Long.valueOf("1"))).thenReturn(Optional.ofNullable(userOne));
        this.mockMvc.perform(get("/api/users/" + "1")).andDo(print()).andExpect(status().isOk());
    }
    @Test
    void createUser() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(userOne);

        when(userServiceImpl.createUser(userOne)).thenReturn("Success");
        this.mockMvc.perform(post("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print()).andExpect(status().isOk());
    }
    @Test
    void updateUser() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(SerializationFeature.WRAP_ROOT_VALUE, false);
        ObjectWriter ow = mapper.writer().withDefaultPrettyPrinter();
        String requestJson=ow.writeValueAsString(userOne);

        when(userServiceImpl.updateUser(userOne))
                .thenReturn("User Updated Successfully");
        this.mockMvc.perform(put("/api/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestJson))
                .andDo(print()).andExpect(status().isOk());
    }
    @Test
    void deleteUser() throws Exception {
        when(userServiceImpl.deleteUser(Long.valueOf("1")))
                .thenReturn("User Deleted Successfully");
        this.mockMvc.perform(delete("/api/users/" + "1"))
                .andDo(print()).andExpect(status().isOk());

    }
}
