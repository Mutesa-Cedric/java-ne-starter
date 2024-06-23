package com.supamenu.www.dtos.user;

import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import lombok.*;

@Data
@RequiredArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UpdateUserDTO {
    @Schema(example = "John")
    private String firstName;

    @Schema(example = "Doe")
    private String lastName;

    @Schema(example = "johndoe")
    private String username;

    @Schema(example = "example@gmail.com")
    @Email(message = "Invalid email format")
    private String email;
}

