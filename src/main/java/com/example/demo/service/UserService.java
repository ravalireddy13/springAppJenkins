package com.example.demo.service;

import com.example.demo.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {
    public String createUser(User user);
    public String updateUser(User user);
    public String deleteUser(Long id);
    public Optional<User> getUserById(Long id);
    public List<User> getAllUsers();
}

