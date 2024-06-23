package com.java_ne.dtos.role;

import com.java_ne.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
@AllArgsConstructor
public class RolesResponseDTO {
    private Page<Role> roles;
}
