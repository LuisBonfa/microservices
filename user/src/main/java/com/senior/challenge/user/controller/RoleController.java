package com.senior.challenge.user.controller;

import com.senior.challenge.user.dto.RoleDTO;
import com.senior.challenge.user.dto.UserDTO;
import com.senior.challenge.user.entity.Role;
import com.senior.challenge.user.entity.User;
import com.senior.challenge.user.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/role")
public class RoleController {

    private final RoleService roleService;

    @Autowired
    public RoleController(RoleService roleService) {
        this.roleService = roleService;
    }

    @PostMapping
    public ResponseEntity<?> save(@RequestBody RoleDTO roleDTO) {
        try {
            return ResponseEntity.ok(roleService.save(Role.create(roleDTO)));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(e);
        }
    }
}
