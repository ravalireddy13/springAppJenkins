package com.example.demo.serviceImpl;

import com.example.demo.exception.UserNotFoundException;
import com.example.demo.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.example.demo.repo.UserRepository;
import com.example.demo.service.UserService;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {
    UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

public String createUser(User user){
     userRepository.save(user);
    return "Success";
}
public List<User> getAllUsers(){
    if(userRepository.findAll().isEmpty())
        throw new UserNotFoundException("There are no users to retrieve");
    return userRepository.findAll();
}
    public Optional<User> getUserById(Long id) {
        if( userRepository.findById(id).isEmpty())
            throw new UserNotFoundException("User does not exist");
    return userRepository.findById(id);
    }

    public String updateUser(User userDetails) {
             userRepository.save(userDetails);
        return "Success";
    }

    public String deleteUser(Long id) {
            userRepository.deleteById(id);
            return "Success";

    }

}
