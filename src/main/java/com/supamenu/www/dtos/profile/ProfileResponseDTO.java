package com.supamenu.www.dtos.profile;

import com.supamenu.www.models.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
@AllArgsConstructor
public class ProfileResponseDTO {
    private User user;
}
