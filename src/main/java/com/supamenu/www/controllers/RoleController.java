package com.supamenu.www.controllers;

import com.supamenu.www.dtos.response.ApiResponse;
import com.supamenu.www.dtos.role.CreateRoleDTO;
import com.supamenu.www.dtos.role.RoleResponseDTO;
import com.supamenu.www.dtos.role.RolesResponseDTO;
import com.supamenu.www.services.interfaces.RoleService;
import com.supamenu.www.utils.Constants;
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
