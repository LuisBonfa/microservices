package com.senior.challenge.user.service;

import com.senior.challenge.user.entity.Role;
import com.senior.challenge.user.entity.User;
import com.senior.challenge.user.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public User save(User user) {
        user.setCreationDate();
        user.setStatus("new");

        List<Role> roles = new ArrayList<>();
        return userRepository.save(user);
    }

    public User update(User user) {

        Optional<User> newClient = userRepository.findById(user.getId());
        if (newClient.isPresent()) {
            user.setCreatedAt(newClient.get().getCreatedAt());
            return userRepository.save(user);
        } else
            return null;
    }

    public Object delete(UUID id) {
        Optional<User> newClient = userRepository.findById(id);
        if (newClient.isPresent()) {
            userRepository.delete(newClient.get());
            return true;
        } else
            return false;
    }

    public Object findUserById(UUID id) {
        Optional<User> newClient = userRepository.findById(id);
        if (newClient.isPresent()) {
            return newClient.get();
        } else
            return new Object();
    }

    public Object findAll() {
        return userRepository.findAll();
    }
}
