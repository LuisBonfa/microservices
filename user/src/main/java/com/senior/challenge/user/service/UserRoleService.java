package com.senior.challenge.user.service;

import com.senior.challenge.user.entity.Role;
import com.senior.challenge.user.entity.User;
import com.senior.challenge.user.entity.UserRole;
import com.senior.challenge.user.repository.RoleRepository;
import com.senior.challenge.user.repository.UserRoleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserRoleService {

    private final UserRoleRepository userRoleRepository;
    private final RoleRepository roleRepository;

    @Autowired
    public UserRoleService(UserRoleRepository userRoleRepository, RoleRepository roleRepository) {
        this.userRoleRepository = userRoleRepository;
        this.roleRepository = roleRepository;
    }


    public List<UserRole> loadUserRolesFromUserDTO(List<String> roles, User user){
        List<UserRole> userRoles = new ArrayList<>();
        for(String name: roles){
            Optional<Role> role = roleRepository.findByName(name);
            if(role.isPresent()){
                UserRole userRole = new UserRole();
                userRole.setCreationDate();
                userRole.setRole(role.get());
                userRole.setUser(user);
                userRoles.add(userRole);
            }
        }
        return userRoles;
    }
}
