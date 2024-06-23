package com.java_ne.controllers;

import com.java_ne.dtos.response.ApiResponse;
import com.java_ne.dtos.profile.ChangePasswordRequestDTO;
import com.java_ne.dtos.profile.ProfileResponseDTO;
import com.java_ne.dtos.profile.UpdateProfileRequestDTO;
import com.java_ne.services.interfaces.ProfileService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/profile")
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('USER')")
public class ProfileController {
    private final ProfileService profileService;

    @GetMapping("/get-profile")
    public ResponseEntity<ApiResponse<ProfileResponseDTO>> getProfile() {
        return profileService.getProfile();
    }

    @PatchMapping("/update-profile")
    public ResponseEntity<ApiResponse<ProfileResponseDTO>> updateProfile(@Valid @RequestBody UpdateProfileRequestDTO updateProfileRequestDTO) {
        return profileService.updateProfile(updateProfileRequestDTO);
    }

    @PatchMapping("/change-password")
    public ResponseEntity<ApiResponse<ProfileResponseDTO>> changePassword(@Valid @RequestBody ChangePasswordRequestDTO
                                                                                  changePasswordRequestDTO) {
        return profileService.changePassword(changePasswordRequestDTO);
    }
}
