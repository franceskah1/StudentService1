package com.example.studentservice.repositories;

import com.example.studentservice.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User,Long> {
    Optional<User> findByEmail(String email);

    boolean existsByEmail(String email);
    boolean existsByUsername(String userName);


    Optional<Object> findAllByIsEnabled(Boolean isEnabled);
}
