package com.java_ne.services.implementations;

import com.java_ne.dtos.user.*;
import com.java_ne.enumerations.user.EUserRole;
import com.java_ne.exceptions.ConflictException;
import com.java_ne.exceptions.CustomException;
import com.java_ne.exceptions.InternalServerErrorException;
import com.java_ne.dtos.response.ApiResponse;
import com.java_ne.exceptions.NotFoundException;
import com.java_ne.repositories.IRoleRepository;
import com.java_ne.services.interfaces.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.java_ne.enumerations.user.EUserStatus;
import com.java_ne.models.Role;
import com.java_ne.models.User;
import com.java_ne.repositories.IUserRepository;
import com.java_ne.utils.HashUtil;

import java.util.*;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final IUserRepository userRepository;
    private final RoleServiceImpl roleService;
    private final IRoleRepository roleRepository;

    @Override
    public User createUserEntity(CreateUserDTO createUserDTO) {
        Optional<User> foundUser = userRepository.findUserByEmailOrUsername(createUserDTO.getEmail(), createUserDTO.getUsername());
        if (foundUser.isPresent())
            throw new ConflictException("The user with the given email or username already exists");
        User user = new User();
        Role role = roleService.getRoleByName(EUserRole.USER);
        Set<Role> roles = new HashSet<>();
        roles.add(role);
        user.setUsername(createUserDTO.getUsername());
        user.setFirstName(createUserDTO.getFirstName());
        user.setLastName(createUserDTO.getLastName());
        user.setStatus(EUserStatus.ACTIVE);
        user.setEmail(createUserDTO.getEmail());
        user.setUsername(createUserDTO.getUsername());
        user.setPassword(HashUtil.hashPassword(createUserDTO.getPassword()));
        user.setFullName(createUserDTO.getFirstName() + " " + createUserDTO.getLastName());
        user.setRoles(roles);
        return user;
    }

    @Override
    @Transactional
    public ResponseEntity<ApiResponse<UserResponseDTO>> createUser(CreateUserDTO createUserDTO) {
        try {
            User user = createUserEntity(createUserDTO);
            userRepository.save(user);
            return ApiResponse.success("Successfully created user", HttpStatus.CREATED, new UserResponseDTO(user));
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<UsersResponseDTO>> getUsers(Pageable pageable) {
        try {
            Page<User> users = userRepository.findAll(pageable);
            for (User user : users) {
                user.setFullName(user.getFirstName() + " " + user.getLastName());
            }
            return ApiResponse.success("Successfully fetched all users", HttpStatus.OK, new UsersResponseDTO(users));
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ApiResponse<UserResponseDTO>> getUserById(Long uuid) {
        try {
            User user = findUserById(uuid);
            return ApiResponse.success("Successfully fetched user", HttpStatus.OK, new UserResponseDTO(user));
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }

    public User findUserById(Long userId) {
        return userRepository.findById(userId).orElseThrow(() -> new NotFoundException("The Resource was not found"));
    }

    @Override
    @Transactional
    public ResponseEntity<ApiResponse<UserResponseDTO>> updateUser(Long userId, UpdateUserDTO updateUserDTO) {
        try {
            User user = findUserById(userId);
            if (user.getEmail() != null) user.setEmail(updateUserDTO.getEmail());
            if (user.getFirstName() != null) user.setFirstName(updateUserDTO.getFirstName());
            if (user.getLastName() != null) user.setLastName(updateUserDTO.getLastName());
            if (user.getUsername() != null) user.setUsername(updateUserDTO.getUsername());
            return ApiResponse.success("Successfully updated the user", HttpStatus.OK, new UserResponseDTO(user));
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }

    @Override
    public User getLoggedInUser() {
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails) {
            username = ((UserDetails) principal).getUsername();
        } else {
            username = principal.toString();
        }
        User user = userRepository.findUserByUsername(username).orElseThrow(() -> new NotFoundException("User Not Found"));
        user.setFullName(user.getFirstName() + " " + user.getLastName());
        return user;
    }

    @Override
    @Transactional
    public ResponseEntity<ApiResponse<UserResponseDTO>> addRoles(Long userId, UserRoleModificationDTO userRoleModificationDTO) {
        try {
            User user = findUserById(userId);
            Set<Role> roles = user.getRoles();
            for (Long roleId : userRoleModificationDTO.getRoles()) {
                Role role = roleRepository.findById(roleId).orElseThrow(() -> new NotFoundException("Role not found"));
                roles.add(role);
            }
            user.setRoles(roles);
            userRepository.save(user);
            return ApiResponse.success("Successfully added roles to the user", HttpStatus.OK, new UserResponseDTO(user));
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<UserResponseDTO>> removeRoles(Long userId, UserRoleModificationDTO userRoleModificationDTO) {
        try {
            User user = findUserById(userId);
            Set<Role> roles = user.getRoles();
            for (Long roleId : userRoleModificationDTO.getRoles()) {
                Role role = roleRepository.findById(roleId).orElseThrow(() -> new NotFoundException("Role not found"));
                roles.remove(role);
            }
            user.setRoles(roles);
            userRepository.save(user);
            return ApiResponse.success("Successfully removed roles from the user", HttpStatus.OK, new UserResponseDTO(user));
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }

    @Override
    @Transactional
    public ResponseEntity<ApiResponse<Object>> deleteUser(Long userId) {
        try {
            User user = findUserById(userId);
            userRepository.deleteById(userId);
            return ApiResponse.success("Successfully deleted the user", HttpStatus.OK, null);
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }
}
