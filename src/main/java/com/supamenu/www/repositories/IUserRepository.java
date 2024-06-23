package com.supamenu.www.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.supamenu.www.models.User;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IUserRepository extends JpaRepository<User, Long> {
    Optional<User> findUserByEmailOrUsername(String email, String username);

    Optional<User> findUserByEmail(String email);

    Optional<User> findUserByUsername(String username);
}
