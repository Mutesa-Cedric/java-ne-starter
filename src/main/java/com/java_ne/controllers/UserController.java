package com.java_ne.controllers;

import com.java_ne.dtos.response.ApiResponse;
import com.java_ne.dtos.user.*;
import com.java_ne.services.interfaces.UserService;
import com.java_ne.utils.Constants;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST})
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
@PreAuthorize("hasAnyAuthority('ADMIN')")
public class UserController {
    private final UserService userService;

    @PostMapping("/create-user")
    public ResponseEntity<ApiResponse<UserResponseDTO>> createUser(@RequestBody CreateUserDTO createUserDTO) {
        return this.userService.createUser(createUserDTO);
    }

    @GetMapping("/get-users")
    public ResponseEntity<ApiResponse<UsersResponseDTO>> getUsers(
            @RequestParam(value = "page", defaultValue = Constants.DEFAULT_PAGE_NUMBER) int page,
            @RequestParam(value = "size", defaultValue = Constants.DEFAULT_PAGE_SIZE) int limit
    ) {
        Pageable pageable = (Pageable) PageRequest.of(page, limit, Sort.Direction.ASC, "id");
        return userService.getUsers(pageable);
    }

    @GetMapping("/get-user/{userId}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> getUserById(@PathVariable Long userId) {
        return userService.getUserById(userId);
    }

    @PatchMapping("/update-user/{userId}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> updateUser(@PathVariable Long userId, @RequestBody UpdateUserDTO updateUserDTO) {
        return userService.updateUser(userId, updateUserDTO);
    }

    @DeleteMapping("/delete-user/{userId}")
    public ResponseEntity<ApiResponse<Object>> deleteUser(@PathVariable Long userId) {
        return userService.deleteUser(userId);
    }

    @PatchMapping("/add-roles/{userId}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> addRoles(@Valid @PathVariable("userId") Long userId, @Valid @RequestBody UserRoleModificationDTO userRoleModificationDTO) {
        return userService.addRoles(userId, userRoleModificationDTO);
    }

    @PatchMapping("/remove-roles/{userId}")
    public ResponseEntity<ApiResponse<UserResponseDTO>> removeRoles(@Valid @PathVariable("userId") Long userId, @Valid @RequestBody UserRoleModificationDTO userRoleModificationDTO) {
        return userService.removeRoles(userId, userRoleModificationDTO);
    }
}
