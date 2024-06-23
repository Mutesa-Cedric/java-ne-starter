package com.java_ne.dtos.profile;

import com.java_ne.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ProfileResponseDTO {
    private User user;
}
