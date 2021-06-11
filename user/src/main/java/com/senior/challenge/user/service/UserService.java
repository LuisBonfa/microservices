package com.senior.challenge.user.service;

import com.senior.challenge.user.controller.error.UpdateUserFailureException;
import com.senior.challenge.user.dto.UserDTO;
import com.senior.challenge.user.entity.User;
import com.senior.challenge.user.entity.UserRole;
import com.senior.challenge.user.error.StandardException;
import com.senior.challenge.user.repository.UserRepository;
import com.senior.challenge.user.utils.PatternValidator;
import com.senior.challenge.user.utils.VerifyDtoFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

        User user = User.create(userDTO);
        user.setCreationDate();
        user.setTries(0);
        user.setStatus("enabled");

        List<UserRole> userRoles = userRoleService.loadUserRolesFromUserDTO(userDTO.getRoles(), user);
        user.setUserRoles(userRoles);
        userRepository.save(user);

        return user;
    }

    public User update(UserDTO userDTO, UUID userId) throws StandardException {
        Optional<User> u = null;
        try {
            u = userRepository.findById(userId).map(record -> {
                VerifyDtoFields.verifyNullAndAddToObject(userDTO, record);
                record.setUpdatedAt(new Date());
                userRepository.save(record);
                return record;
            });
        } catch (Exception e) {
            throw new UpdateUserFailureException("Error Updating123 User", e);
        }

        return u.get();
    }

    public Object delete(UUID id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            userRepository.delete(user.get());
            return true;
        } else
            return false;
    }

    public Object findById(UUID id) {
        Optional<User> user = userRepository.findById(id);
        if (user.isPresent()) {
            return user.get();
        } else
            return null;
    }

    public User findByName(String name) {
        Optional<User> user = userRepository.findByName(name);
        if (user.isPresent()) {
            return user.get();
        } else
            return null;
    }

    public User findByPhoneOrDocument(String data) {
        List<User> user = userRepository.findByDocumentOrPhone(data, data);
        if (!user.isEmpty())
            return user.get(0);
        else
            return null;
    }

    public User findByMultipleChoices(String param) {
        User user = null;
        if (PatternValidator.nameMatch(param))
            user = findByName(param);
        else
            user = findByPhoneOrDocument(param);

        return user;
    }

    public Object findAll() {
        return userRepository.findAll();
    }
}
