package com.femcoders.periodico_ayer.service;

import com.femcoders.periodico_ayer.dto.request.UserRequest;
import com.femcoders.periodico_ayer.dto.response.UserResponse;
import com.femcoders.periodico_ayer.entity.User;
import com.femcoders.periodico_ayer.mapper.UserMapper;
import com.femcoders.periodico_ayer.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UserServiceImplTest {

    @Mock
    private UserRepository userRepository;

    @Mock
    private UserMapper userMapper;

    @InjectMocks
    private UserServiceImpl userService;

    private UserRequest request;
    private User user;
    private UserResponse response;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);

        request = new UserRequest();
        request.setUsername("Hector");
        request.setEmail("hector@lavoe.com");

        user = new User();
        user.setId(1L);
        user.setUsername("Hector");
        user.setEmail("hector@lavoe.com");

        response = new UserResponse(1L, "Hector", "hector@lavoe.com");
    }

    @Test
    void addUser_success() {
        when(userMapper.toEntity(request)).thenReturn(user);
        when(userRepository.save(user)).thenReturn(user);
        when(userMapper.toResponse(user)).thenReturn(response);

        ResponseEntity<UserResponse> result = userService.addUser(request);

        assertEquals(HttpStatus.CREATED, result.getStatusCode());
        assertEquals(response, result.getBody());
        verify(userRepository, times(1)).save(user);
    }

    @Test
    void getAllUsers_returnsList() {
        when(userRepository.findAll()).thenReturn(List.of(user));

        ResponseEntity<List<User>> result = userService.getAllUsers();

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(1, result.getBody().size());
    }

    @Test
    void getUserById_found() {
        when(userRepository.findById(1L)).thenReturn(Optional.of(user));

        ResponseEntity<User> result = userService.getUserById(1L);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals(user, result.getBody());
    }

    @Test
    void getUserById_notFound() {
        when(userRepository.findById(99L)).thenReturn(Optional.empty());

        ResponseEntity<User> result = userService.getUserById(99L);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void updateUser_found() {
        User updated = new User();
        updated.setUsername("Nuevo nombre");
        updated.setEmail("nuevo@mail.com");

        when(userRepository.findById(1L)).thenReturn(Optional.of(user));
        when(userRepository.save(any(User.class))).thenReturn(updated);

        ResponseEntity<User> result = userService.updateUser(1L, updated);

        assertEquals(HttpStatus.OK, result.getStatusCode());
        assertEquals("Nuevo nombre", result.getBody().getUsername());
    }

    @Test
    void updateUser_notFound() {
        when(userRepository.findById(1L)).thenReturn(Optional.empty());

        ResponseEntity<User> result = userService.updateUser(1L, user);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
    }

    @Test
    void deleteUser_success() {
        when(userRepository.existsById(1L)).thenReturn(true);

        ResponseEntity<Void> result = userService.deleteUser(1L);

        assertEquals(HttpStatus.NO_CONTENT, result.getStatusCode());
        verify(userRepository, times(1)).deleteById(1L);
    }

    @Test
    void deleteUser_notFound() {
        when(userRepository.existsById(99L)).thenReturn(false);

        ResponseEntity<Void> result = userService.deleteUser(99L);

        assertEquals(HttpStatus.NOT_FOUND, result.getStatusCode());
        verify(userRepository, never()).deleteById(any());
    }
}
