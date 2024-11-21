package com.example.demo.serviceImpl;

import com.example.demo.model.User;
import com.example.demo.repo.UserRepository;
import com.example.demo.service.UserService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Answers;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.*;

public class UserServiceImplTest {
    @Mock
    private UserRepository userRepository;
    private UserService userService;
    AutoCloseable autoCloseable;
    User user;

    @BeforeEach
    void setUp() {
        autoCloseable = MockitoAnnotations.openMocks(this);
        userService = new UserServiceImpl(userRepository);
        user = new User(1,"password","meravalireddy@gmail.com","Rava","S",1234567890);
    }

    @AfterEach
    void tearDown() throws Exception {
        autoCloseable.close();
    }

    @Test
    void testCreateUser() {
        mock(User.class);
        mock(UserRepository.class);
        when(userRepository.save(user)).thenReturn(user);
        assertThat(userService.createUser(user)).isEqualTo("Success");

    }

    @Test
    void testUpdateUser() {
        mock(User.class);
        mock(UserRepository.class);

        when(userRepository.save(user)).thenReturn(user);
        assertThat(userService.updateUser(user)).isEqualTo("Success");
    }

    @Test
    void testDeleteUser() {
        mock(User.class);
        mock(UserRepository.class, Mockito.CALLS_REAL_METHODS);

        doAnswer(Answers.CALLS_REAL_METHODS).when(userRepository)
                .deleteById(any());
        assertThat(userService.deleteUser(Long.valueOf("1"))).isEqualTo("Success");
    }
    @Test
    void testGetUserById() {
        mock(User.class);
        mock(UserRepository.class);

        when(userRepository.findById(Long.valueOf("1"))).thenReturn(Optional.ofNullable(user));

        assertThat(userService.getUserById(Long.valueOf("1")).get().getFirstName())
                .isEqualTo(user.getFirstName());
    }

    @Test
    void testGetAllUsers() {
        mock(User.class);
        mock(UserRepository.class);

        when(userRepository.findAll()).thenReturn(new ArrayList<User>(
                Collections.singleton(user)
        ));

        assertThat(userService.getAllUsers().get(0).getFirstName()).
                isEqualTo(user.getFirstName());

    }


}