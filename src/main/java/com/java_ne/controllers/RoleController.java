package com.java_ne.controllers;

import com.java_ne.dtos.response.ApiResponse;
import com.java_ne.dtos.role.CreateRoleDTO;
import com.java_ne.dtos.role.RolesResponseDTO;
import com.java_ne.utils.Constants;
import com.java_ne.dtos.role.RoleResponseDTO;
import com.java_ne.services.interfaces.RoleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/roles")
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class RoleController {
    private final RoleService roleService;

    @PostMapping("/create-role")
    public ResponseEntity<ApiResponse<RoleResponseDTO>> createRole(@Valid @RequestBody CreateRoleDTO createRoleDTO) {
        return roleService.createRole(createRoleDTO);
    }

    @GetMapping("/get-roles")
    public ResponseEntity<ApiResponse<RolesResponseDTO>> getAllRoles(
            @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = Constants.DEFAULT_PAGE_SIZE) int limit
    ) {
        Pageable pageable = (Pageable) PageRequest.of(page, limit, Sort.Direction.ASC, "id");
        return roleService.getRoles(pageable);
    }
}
