package com.java_ne.services.interfaces;

import com.java_ne.dtos.response.ApiResponse;
import com.java_ne.dtos.role.CreateRoleDTO;
import com.java_ne.dtos.role.RoleResponseDTO;
import com.java_ne.dtos.role.RolesResponseDTO;
import com.java_ne.enumerations.user.EUserRole;
import com.java_ne.models.Role;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

public interface RoleService {
    public Role getRoleById(Long roleId);

    public Role getRoleByName(EUserRole roleName);

    public void createRole(EUserRole roleName);

    public ResponseEntity<ApiResponse<RoleResponseDTO>> createRole(CreateRoleDTO createRoleDTO);

    public ResponseEntity<ApiResponse<RolesResponseDTO>> getRoles(Pageable pageable);

    public Role deleteRole(Long roleId);

    public boolean isRolePresent(EUserRole roleName);
}
