package com.supamenu.www.services.interfaces;

import com.supamenu.www.dtos.response.ApiResponse;
import com.supamenu.www.dtos.role.CreateRoleDTO;
import com.supamenu.www.dtos.role.RoleResponseDTO;
import com.supamenu.www.dtos.role.RolesResponseDTO;
import com.supamenu.www.enumerations.user.EUserRole;
import com.supamenu.www.models.Role;
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
