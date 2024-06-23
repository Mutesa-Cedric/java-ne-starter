package com.java_ne.services.interfaces;

import com.java_ne.dtos.response.ApiResponse;
import com.java_ne.dtos.profile.ChangePasswordRequestDTO;
import com.java_ne.dtos.profile.ProfileResponseDTO;
import com.java_ne.dtos.profile.UpdateProfileRequestDTO;
import org.springframework.http.ResponseEntity;

public interface ProfileService {
    public ResponseEntity<ApiResponse<ProfileResponseDTO>> getProfile();

    public ResponseEntity<ApiResponse<ProfileResponseDTO>> updateProfile(UpdateProfileRequestDTO updateProfileRequestDTO);

    public ResponseEntity<ApiResponse<ProfileResponseDTO>> changePassword(ChangePasswordRequestDTO changePasswordRequestDTO);
}
