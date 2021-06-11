package com.senior.challenge.user.utils;

import com.senior.challenge.user.entity.Role;
import com.senior.challenge.user.entity.User;
import com.senior.challenge.user.entity.UserRole;
import com.senior.challenge.user.repository.RoleRepository;
import com.senior.challenge.user.repository.UserRepository;
import com.senior.challenge.user.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Component
@Scope(value = "prototype")
public class InitializationControl implements CommandLineRunner {

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    UserRepository userRepository;

    @Autowired
    UserRoleRepository userRoleRepository;

    @Override
    public void run(String... args) throws Exception {
        Optional<Role> role = roleRepository.findByName("admin");
        Optional<User> user = userRepository.findByName("root");

        if (!user.isPresent()) {
            User rootUser = new User();
            rootUser.setName("root");
            rootUser.setPassword("$2a$10$slYQmyNdGzTn7ZLBXBChFOC9f6kFjAqPhccnP6DxlWXx2lPk1C3G6");
            rootUser.setAlias("root");
            rootUser.setTries(0);
            rootUser.setEmail("luisgbonfaprof@gmail.com");
            rootUser.setPhone("12988914622");
            rootUser.setDocument("34656312851");
            rootUser.setStatus("enabled");
            rootUser.setCreationDate();
            userRepository.save(rootUser);

            UserRole userRole = new UserRole();
            userRole.setUser(rootUser);
            userRole.setRole(role.get());
            userRole.setCreationDate();
            userRoleRepository.save(userRole);

        } else
            System.out.println("Root Already Registered");
    }
}