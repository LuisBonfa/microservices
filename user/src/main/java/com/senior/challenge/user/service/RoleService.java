package com.senior.challenge.user.service;

import com.senior.challenge.user.entity.Role;
import com.senior.challenge.user.error.StandardException;
import com.senior.challenge.user.repository.RoleRepository;
import com.senior.challenge.user.service.error.RoleSaveFailureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import javax.persistence.PersistenceException;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public Role save(Role role) throws StandardException {
        Optional<Role> newRole = roleRepository.findByName(role.getName());
        if(!Optional.empty().isPresent())
            return roleRepository.save(role);
        else
            throw new RoleSaveFailureException("Error Saving Role");
    }
}
