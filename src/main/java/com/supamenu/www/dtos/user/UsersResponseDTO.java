package com.supamenu.www.dtos.user;

import com.supamenu.www.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
@AllArgsConstructor
public class UsersResponseDTO {
    Page<User> users;
}
