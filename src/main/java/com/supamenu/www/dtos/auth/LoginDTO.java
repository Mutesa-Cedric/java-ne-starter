package com.supamenu.www.dtos.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class LoginDTO {
    @Schema(example = "example@gmail.com")
    @NotBlank(message = "Email is required")
    @Email(message = "Email should be valid")
    private String email;

    @Schema(example = "password@123")
    @NotBlank(message = "Password is required")
    private String password;
}


