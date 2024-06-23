package com.supamenu.www.services.interfaces;

import com.supamenu.www.dtos.profile.ChangePasswordRequestDTO;
import com.supamenu.www.dtos.profile.ProfileResponseDTO;
import com.supamenu.www.dtos.profile.UpdateProfileRequestDTO;
import com.supamenu.www.dtos.response.ApiResponse;
import com.supamenu.www.models.User;
import org.springframework.http.ResponseEntity;

public interface ProfileService {
    public ResponseEntity<ApiResponse<ProfileResponseDTO>> getProfile();

    public ResponseEntity<ApiResponse<ProfileResponseDTO>> updateProfile(UpdateProfileRequestDTO updateProfileRequestDTO);

    public ResponseEntity<ApiResponse<ProfileResponseDTO>> changePassword(ChangePasswordRequestDTO changePasswordRequestDTO);
}
