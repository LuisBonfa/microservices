package com.senior.challenge.user.repository;

import com.senior.challenge.user.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface UserRepository extends JpaRepository<User, UUID>{
    Optional<User> findByName(@Param("name") String name);

    List<User> findByDocumentOrPhoneOrNameContaining(@Param("document") String document, @Param("phone") String phone, @Param("name") String name);
}
