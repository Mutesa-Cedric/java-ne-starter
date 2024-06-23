package com.java_ne.dtos.role;

import com.java_ne.enumerations.user.EUserRole;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
public class CreateRoleDTO {
    @Schema(example = "ADMIN", description = "Role name")
//    @NotBlank(message = "Role name is required")
    private EUserRole name;
}
