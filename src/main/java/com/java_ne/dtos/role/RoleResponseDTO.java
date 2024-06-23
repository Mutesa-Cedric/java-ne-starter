package com.java_ne.dtos.role;

import com.java_ne.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class RoleResponseDTO {
    private Role role;
}
