package com.java_ne.repositories;

import com.java_ne.enumerations.user.EUserRole;
import com.java_ne.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByName(EUserRole name);
}
