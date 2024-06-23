package com.supamenu.www.services.implementations;

import com.supamenu.www.dtos.auth.LoginDTO;
import com.supamenu.www.dtos.auth.RegisterUserDTO;
import com.supamenu.www.dtos.response.ApiResponse;
import com.supamenu.www.dtos.user.CreateUserDTO;
import com.supamenu.www.exceptions.CustomException;
import com.supamenu.www.models.User;
import com.supamenu.www.repositories.IUserRepository;
import com.supamenu.www.security.JwtTokenProvider;
import com.supamenu.www.security.UserPrincipal;
import com.supamenu.www.services.interfaces.AuthenticationService;
import com.supamenu.www.services.interfaces.UserService;
import com.supamenu.www.dtos.auth.AuthResponse;
import com.supamenu.www.utils.UserUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
    private final UserService userService;
    private final IUserRepository userRepository;
    private final JwtTokenProvider jwtTokenProvider;
    private final AuthenticationProvider authenticationProvider;

    @Override
    public ResponseEntity<ApiResponse<AuthResponse>> register(RegisterUserDTO registerUserDTO) {
        try {
            CreateUserDTO createUserDTO = new CreateUserDTO(registerUserDTO);
            User user = userService.createUserEntity(createUserDTO);
            userRepository.save(user);
            Authentication authentication = authenticateUser(new LoginDTO(createUserDTO.getEmail(), createUserDTO.getPassword()));
            AuthResponse response = generateJwtAuthenticationResponse(authentication);
            return ApiResponse.success("Successfully registered user", HttpStatus.OK, response);
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }

    @Override
    public ResponseEntity<ApiResponse<AuthResponse>> login(LoginDTO loginDTO) {
        try {
            Authentication authentication = authenticateUser(loginDTO);
            AuthResponse response = generateJwtAuthenticationResponse(authentication);
            return ApiResponse.success("Successfully logged in", HttpStatus.OK, response);
        } catch (Exception e) {
            throw new CustomException(e);
        }
    }

    private Authentication authenticateUser(LoginDTO loginDTO) {
        UsernamePasswordAuthenticationToken authRequest =
                new UsernamePasswordAuthenticationToken(loginDTO.getEmail(), loginDTO.getPassword());
        Authentication authentication = authenticationProvider.authenticate(authRequest);
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }

    private AuthResponse generateJwtAuthenticationResponse(Authentication authentication) {
        String jwt = jwtTokenProvider.generateAccessToken(authentication);
        UserPrincipal userPrincipal = UserUtils.getLoggedInUser();
        assert userPrincipal != null;
        User user = userService.findUserById(userPrincipal.getId());
        user.setFullName(user.getFirstName() + " " + user.getLastName());
        return new AuthResponse(jwt, user);
    }
}
