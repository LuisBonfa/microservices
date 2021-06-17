package com.senior.challenge.user.service;

import com.senior.challenge.user.dto.UserDTO;
import com.senior.challenge.user.entity.User;
import com.senior.challenge.user.entity.UserRole;
import com.senior.challenge.user.repository.UserRepository;
import com.senior.challenge.user.utils.Security;
import com.senior.challenge.user.utils.VerifyDtoFields;
import javassist.NotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;
    private final UserRoleService userRoleService;

    @Autowired
    public UserService(UserRepository userRepository, UserRoleService userRoleService) {
        this.userRepository = userRepository;
        this.userRoleService = userRoleService;
    }

    public User save(UserDTO userDTO) {
        try {
            var user = User.create(userDTO);
            user.setCreationDate();
            user.setTries(0);
            user.setPassword(Security.hash(userDTO.getPassword()));
            user.setStatus("enabled");

            List<UserRole> userRoles = userRoleService.loadUserRolesFromUserDTO(userDTO.getRoles(), user);
            user.setUserRoles(userRoles);
            userRepository.save(user);

            return user;

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error Creating User", e);
        }
    }

    public User update(UserDTO userDTO, UUID userId) {
        try {
            Optional<User> u = userRepository.findById(userId).map(user -> {
                VerifyDtoFields.verifyNullAndAddToObject(userDTO, user);
                if (Optional.ofNullable(userDTO.getPassword()).isPresent()) {
                    try {
                        user.setPassword(Security.hash(user.getPassword()));
                    } catch (InvalidKeySpecException | NoSuchAlgorithmException e) {
                        throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error securing password", e);
                    }
                }
                user.setUpdatedAt(new Date());
                userRepository.save(user);
                return user;
            });

            if (u.isEmpty())
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "User Not Found");

            return u.get();

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error Updating User", e);
        }
    }

    public Object findById(UUID id) {
        try {
            Optional<User> user = userRepository.findById(id);
            if (user.isEmpty())
                throw new NotFoundException("User Not Found");

            return user.get();

        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error Finding User By Name", e);
        }
    }

    public List<User> findByNameContaining(String name) {
        try {
            return userRepository.findByNameContaining(name);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error Finding User By Name", e);
        }
    }

    public List<User> findByDocumentOrPhoneOrNameContaining(String param) {
        try {
            return userRepository.findByDocumentOrPhoneOrNameContaining(param, param, param);
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error Listing User by Multiple Choice", e);
        }
    }

    public Object findAll() {
        try {
            return userRepository.findAll();
        } catch (Exception e) {
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR, "Error listing Users", e);
        }
    }
}
