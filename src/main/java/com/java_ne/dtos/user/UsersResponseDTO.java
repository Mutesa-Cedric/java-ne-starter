package com.java_ne.dtos.user;

import com.java_ne.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import org.springframework.data.domain.Page;

@Data
@AllArgsConstructor
public class UsersResponseDTO {
    Page<User> users;
}
