package com.supamenu.www.dtos.role;

import com.supamenu.www.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoleResponseDTO {
    private Role role;
}
