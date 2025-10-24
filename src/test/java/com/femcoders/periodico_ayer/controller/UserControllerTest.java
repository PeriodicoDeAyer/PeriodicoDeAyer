package com.femcoders.periodico_ayer.controller;

import com.femcoders.periodico_ayer.dto.request.UserRequest;
import com.femcoders.periodico_ayer.dto.response.UserResponse;
import com.femcoders.periodico_ayer.entity.User;
import com.femcoders.periodico_ayer.service.UserService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.http.MediaType;

import java.util.List;

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;

@WebMvcTest(UserController.class)
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserService userService;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void testGetAllUsers() throws Exception {
        when(userService.getAllUsers()).thenReturn(ResponseEntity.ok(List.of()));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users"))
                .andExpect(status().isOk());
    }

    @Test
    void testCreateUser() throws Exception {
        UserRequest req = new UserRequest();
        req.setUsername("Hector");
        req.setEmail("hector@lavoe.com");

        UserResponse response = new UserResponse(1L, "Hector", "hector@lavoe.com");

        when(userService.addUser(Mockito.any(UserRequest.class)))
                .thenReturn(new ResponseEntity<>(response, HttpStatus.CREATED));

        mockMvc.perform(MockMvcRequestBuilders.post("/api/v1/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andDo(print())
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("Hector"));
    }

    @Test
    void testGetUserById_found() throws Exception {
        User user = new User();
        user.setId(1L);
        user.setUsername("Hector");
        user.setEmail("hector@lavoe.com");

        when(userService.getUserById(1L)).thenReturn(ResponseEntity.ok(user));

        mockMvc.perform(MockMvcRequestBuilders.get("/api/v1/users/1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.username").value("Hector"));
    }

    @Test
    void testDeleteUser_success() throws Exception {
        when(userService.deleteUser(1L)).thenReturn(ResponseEntity.noContent().build());

        mockMvc.perform(MockMvcRequestBuilders.delete("/api/v1/users/1"))
                .andExpect(status().isNoContent());
    }
}
