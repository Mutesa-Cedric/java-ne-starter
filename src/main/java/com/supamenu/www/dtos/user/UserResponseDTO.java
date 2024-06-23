package com.supamenu.www.dtos.user;

import com.supamenu.www.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class UserResponseDTO {
    private User user;
}
