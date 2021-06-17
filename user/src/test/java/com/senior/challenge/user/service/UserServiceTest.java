package com.senior.challenge.user.service;

import com.senior.challenge.user.dto.UserDTO;
import com.senior.challenge.user.entity.User;
import com.senior.challenge.user.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Profile;
import org.springframework.test.context.ActiveProfiles;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@ActiveProfiles("test")
@Profile("test")
@ExtendWith(MockitoExtension.class)
class UserServiceTest{


    private UserRepository userRepository;
    private Use

    @InjectMocks
    private UserService userService;

    @Test
    void save() {
        UserDTO user = new UserDTO();
        user.setAlias("Thiago");
        user.setDocument("1234567");
        user.setEmail("thiago@gmail.com");
        user.setName("thiago");
        user.setPassword("123456789");
        user.setPhone("5512981126586");
        user.setRoles(Arrays.asList("client"));

        userService.save(user);
        Mockito.verify(userService, Mockito.times(1)).save(user);
    }

    @Test
    void update() {
    }

    @Test
    void findById() {
    }

    @Test
    void findByNameContaining() {
    }

    @Test
    void findByDocumentOrPhoneOrNameContaining() {
    }

    @Test
    void findAll() {
    }
}