package com.supamenu.www.services.implementations;

import com.supamenu.www.dtos.profile.ChangePasswordRequestDTO;
import com.supamenu.www.dtos.profile.ProfileResponseDTO;
import com.supamenu.www.dtos.profile.UpdateProfileRequestDTO;
import com.supamenu.www.dtos.response.ApiResponse;
import com.supamenu.www.exceptions.CustomException;
import com.supamenu.www.models.User;
import com.supamenu.www.repositories.IUserRepository;
import com.supamenu.www.services.interfaces.ProfileService;
import com.supamenu.www.services.interfaces.UserService;
import com.supamenu.www.utils.HashUtil;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {
    private final UserService userService;
    private final IUserRepository userRepository;

    @Override
    public ResponseEntity<ApiResponse<ProfileResponseDTO>> getProfile() {
        try {
            User user = userService.getLoggedInUser();
            return ApiResponse.success(
                    "Profile fetched successfully",
                    HttpStatus.OK,
                    new ProfileResponseDTO(user)
            );
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<ProfileResponseDTO>> updateProfile(UpdateProfileRequestDTO updateProfileRequestDTO) {
        try {
            User user = userService.getLoggedInUser();
            if (updateProfileRequestDTO.getFirstName() != null)
                user.setFirstName(updateProfileRequestDTO.getFirstName());
            if (updateProfileRequestDTO.getLastName() != null) user.setLastName(updateProfileRequestDTO.getLastName());
            if (updateProfileRequestDTO.getEmail() != null) user.setEmail(updateProfileRequestDTO.getEmail());
            if (updateProfileRequestDTO.getUsername() != null) user.setUsername(updateProfileRequestDTO.getUsername());
            userRepository.save(user);
            user.setFullName(user.getFullName());
            return ApiResponse.success("Profile updated successfully", HttpStatus.OK, new ProfileResponseDTO(user));
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<ProfileResponseDTO>> changePassword(ChangePasswordRequestDTO changePasswordRequestDTO) {
        try {
            User user = userService.getLoggedInUser();
            String oldPassword = changePasswordRequestDTO.getOldPassword();
            String newPassword = changePasswordRequestDTO.getNewPassword();
            String confirmPassword = changePasswordRequestDTO.getConfirmPassword();
            if (!newPassword.equals(confirmPassword)) {
                return ApiResponse.error("Passwords do not match", HttpStatus.BAD_REQUEST, null);
            }
            if (!HashUtil.verifyPassword(oldPassword, user.getPassword())) {
                return ApiResponse.error("Old password is incorrect", HttpStatus.BAD_REQUEST, null);
            }
            if (HashUtil.verifyPassword(newPassword, user.getPassword())) {
                return ApiResponse.error("New password cannot be the same as the old password", HttpStatus.BAD_REQUEST, null);
            }
            user.setPassword(HashUtil.hashPassword(newPassword));
            userRepository.save(user);
            return ApiResponse.success(
                    "Password changed successfully",
                    HttpStatus.OK,
                    new ProfileResponseDTO(user)
            );
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }
}
