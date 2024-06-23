package com.supamenu.www.dtos.profile;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class ChangePasswordRequestDTO {
    @Schema(example = "oldPassword")
    @NotBlank(message = "Old password is required")
    private String oldPassword;

    @Schema(example = "newPassword")
    @NotBlank(message = "New password is required")
    private String newPassword;

    @Schema(example = "confirmPassword")
    @NotBlank(message = "Confirm password is required")
    private String confirmPassword;
}
