package com.supamenu.www.dtos.role;

import com.supamenu.www.models.Role;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
@AllArgsConstructor
public class RolesResponseDTO {
    private Page<Role> roles;
}
