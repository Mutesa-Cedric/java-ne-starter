package com.supamenu.www.dtos.auth;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class RegisterUserDTO {
    @Schema(example = "John")
    @NotBlank(message = "First name cannot be blank")
    private String firstName;

    @Schema(example = "Doe")
    @NotBlank(message = "Last name cannot be blank")
    private String lastName;

    @Schema(example = "johndoe")
    @NotBlank(message = "Username cannot be blank")
    private String username;

    @Schema(example = "example@gmail.com")
    @NotBlank(message = "Email cannot be blank")
    @Email(message = "Invalid email format")
    private String email;

    @Schema(example = "password@123")
    @NotBlank(message = "Password cannot be blank")
    @Size(min = 8, message = "Password must be at least 8 characters long")
    private String password;
}
