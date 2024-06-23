package com.java_ne.services.interfaces;

import com.java_ne.dtos.auth.AuthResponse;
import com.java_ne.dtos.auth.LoginDTO;
import com.java_ne.dtos.auth.RegisterUserDTO;
import com.java_ne.dtos.response.ApiResponse;
import org.springframework.http.ResponseEntity;

public interface AuthenticationService {
    public ResponseEntity<ApiResponse<AuthResponse>> login(LoginDTO signInDTO);

    public ResponseEntity<ApiResponse<AuthResponse>> register(RegisterUserDTO registerUserDTO);
}
