package com.senior.challenge.user.service;

import com.senior.challenge.user.entity.Role;
import com.senior.challenge.user.repository.RoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role save(Role newRole) {
        Optional<Role> role = roleRepository.findByName(newRole.getName());
            return roleRepository.save(newRole);
    }

    public Optional<Role> findByName(String name) {
        Optional<Role> role = roleRepository.findByName(name);
        if (!role.isPresent())
            return role;
        else
            return null;
    }

    public List<Role> findAll() {
        return roleRepository.findAll();
    }
}
