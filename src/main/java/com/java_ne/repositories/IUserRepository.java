package com.java_ne.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.java_ne.models.User;

import java.util.Optional;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmailOrUsername(String email, String username);

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByUsername(String username);
}
