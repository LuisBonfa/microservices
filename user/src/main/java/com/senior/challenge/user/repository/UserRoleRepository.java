package com.senior.challenge.user.repository;

import com.senior.challenge.user.entity.User;
import com.senior.challenge.user.entity.UserRole;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface UserRoleRepository extends JpaRepository<UserRole, UUID> {
}
