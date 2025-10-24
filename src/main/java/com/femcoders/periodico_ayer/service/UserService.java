package com.femcoders.periodico_ayer.service;

import com.femcoders.periodico_ayer.dto.request.UserRequest;
import com.femcoders.periodico_ayer.dto.response.UserResponse;
import com.femcoders.periodico_ayer.entity.User;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface UserService {

    ResponseEntity<UserResponse> addUser(UserRequest user);

    ResponseEntity<List<User>> getAllUsers();

    ResponseEntity<User> getUserById(Long id);

    ResponseEntity<User> updateUser(Long id, User user);

    ResponseEntity<Void> deleteUser(Long id);
}
