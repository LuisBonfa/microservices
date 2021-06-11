package com.senior.challenge.user.controller;

import com.senior.challenge.user.controller.error.CreateUserFailureException;
import com.senior.challenge.user.controller.error.UpdateUserFailureException;
import com.senior.challenge.user.dto.UserDTO;
import com.senior.challenge.user.error.StandardException;
import com.senior.challenge.user.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {
    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody UserDTO userDTO) {
        return ResponseEntity.ok(userService.save(userDTO));
    }

    @PutMapping("/{userId}")
    public ResponseEntity<?> update(@PathVariable String userId, @RequestBody UserDTO userDTO) throws Throwable {
        return ResponseEntity.ok(userService.update(userDTO, UUID.fromString(userId)));
    }

    @GetMapping("/{search}")
    public ResponseEntity<?> findByMultipleChoices(@PathVariable String search) {
        try {
            return ResponseEntity.ok(userService.findByMultipleChoices(search));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getCause());
        }
    }

    @GetMapping
    public ResponseEntity<?> findAll() {
        try {
            return ResponseEntity.ok(userService.findAll());
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e.getCause());
        }
    }
}
