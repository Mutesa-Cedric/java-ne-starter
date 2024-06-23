package com.supamenu.www.repositories;

import com.supamenu.www.models.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.supamenu.www.enumerations.user.EUserRole;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface IRoleRepository extends JpaRepository<Role, Long> {
    Optional<Role> findRoleByName(EUserRole name);
}
