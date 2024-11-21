package com.example.demo.controller;

import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.example.demo.serviceImpl.UserServiceImpl;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(origins = "http://localhost:4200")
public class UserController {
    @Autowired
    private UserServiceImpl userServiceImpl;

    @GetMapping
    public ResponseEntity<List<User>> getAllUsers(){
        List<User> users = userServiceImpl.getAllUsers();
        return new ResponseEntity<>(users,HttpStatus.OK);
    }

    @PostMapping
    public String createUser(@RequestBody User user){
    userServiceImpl.createUser(user);
    return "User Created Successfully";
    }

    @GetMapping("/{id}")
    public ResponseEntity<User> getUserById(@PathVariable Long id) {
        Optional<User> user = userServiceImpl.getUserById(id);
        return user.map(value -> new ResponseEntity<>(value, HttpStatus.OK))
                .orElseGet(() -> new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PutMapping()
    public String updateUser(@RequestBody User userDetails) {
       userServiceImpl.updateUser(userDetails);
        return "User Updated Successfully";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable Long id) {
        userServiceImpl.deleteUser(id);
            return "User Deleted Successfully";
    }
}
