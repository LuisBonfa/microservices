package com.senior.challenge.user.service;

import com.senior.challenge.user.entity.Role;
import com.senior.challenge.user.error.StandardException;
import com.senior.challenge.user.repository.RoleRepository;
import com.senior.challenge.user.service.error.RoleSaveFailureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role save(Role newRole) throws StandardException {
        Optional<Role> role = roleRepository.findByName(newRole.getName());
        if(!role.isPresent())
            return roleRepository.save(newRole);
        else
            throw new RoleSaveFailureException("Error Saving Role");
    }

    public Optional<Role> findByName(String name) {
        Optional<Role> role = roleRepository.findByName(name);
        if (!role.isPresent())
            return role;
        else
            return null;
    }
}
