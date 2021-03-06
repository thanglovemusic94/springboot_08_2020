package com.example.employee.service;

import java.util.List;
import java.util.Optional;

import com.example.employee.model.User;


public interface UserService {
    List<User> getAllUser();

    void saveUser(User user);

    void deleteUser(Long id);

    Optional<User> findUserById(Long id);
}
