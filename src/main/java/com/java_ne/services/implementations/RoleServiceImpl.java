package com.java_ne.services.implementations;

import com.java_ne.enumerations.user.EUserRole;
import com.java_ne.exceptions.*;
import com.java_ne.dtos.response.ApiResponse;
import com.java_ne.dtos.role.CreateRoleDTO;
import com.java_ne.dtos.role.RoleResponseDTO;
import com.java_ne.dtos.role.RolesResponseDTO;
import com.java_ne.models.Role;
import com.java_ne.repositories.IRoleRepository;
import com.java_ne.services.interfaces.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;


@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {
    private final IRoleRepository roleRepository;

    @Override
    @Transactional
    public void createRole(EUserRole roleName) {
        Optional<Role> optionalRole = roleRepository.findRoleByName(roleName);
        if (optionalRole.isPresent()) {
            throw new BadRequestException("The role already exists");
        } else {
            Role role = new Role(roleName);
            try {
                roleRepository.save(role);
            } catch (Exception e) {
                throw new InternalServerErrorException(e.getMessage());
            }
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ApiResponse<RoleResponseDTO>> createRole(CreateRoleDTO createRoleDTO) {
        try {

            Optional<Role> optionalRole = roleRepository.findRoleByName(createRoleDTO.getName());
            if (optionalRole.isPresent()) {
                throw new ConflictException("The role already exists");
            } else {
                Role role = new Role(createRoleDTO.getName());
                roleRepository.save(role);
                return ApiResponse.success("Role created successfully", HttpStatus.CREATED, new RoleResponseDTO(role));
            }
        } catch (Exception exception) {
            throw new CustomException(exception);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<RolesResponseDTO>> getRoles(Pageable pageable) {
        try {
            Page<Role> roles = roleRepository.findAll(pageable);
            return ApiResponse.success("Roles fetched successfully", HttpStatus.OK, new RolesResponseDTO(roles));
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }

    @Override
    public Role getRoleById(Long roleId) {
        return roleRepository.findById(roleId).orElseThrow(() -> new NotFoundException("The Role was not found"));
    }

    @Override
    public Role getRoleByName(EUserRole roleName) {
        return roleRepository.findRoleByName(roleName).orElseThrow(() -> new NotFoundException("The Role was not found"));
    }

    @Override
    public Role deleteRole(Long roleId) {
        Role role = roleRepository.findById(roleId).orElseThrow(() -> new NotFoundException("The role is not present"));
        try {
            roleRepository.deleteById(roleId);
            return role;
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }

    @Override
    public boolean isRolePresent(EUserRole roleName) {
        try {
            return roleRepository.findRoleByName(roleName).isPresent();
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }
}
