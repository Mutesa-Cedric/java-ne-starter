package com.supamenu.www.dtos.role;

import com.supamenu.www.enumerations.user.EUserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;

@Data
public class CreateRoleDTO {
    @Schema(example = "ADMIN", description = "Role name")
//    @NotBlank(message = "Role name is required")
    private EUserRole name;
}
