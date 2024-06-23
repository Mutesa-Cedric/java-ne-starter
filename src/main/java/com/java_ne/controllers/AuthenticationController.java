package com.java_ne.controllers;

import com.java_ne.dtos.auth.AuthResponse;
import com.java_ne.dtos.auth.LoginDTO;
import com.java_ne.dtos.auth.RegisterUserDTO;
import com.java_ne.dtos.response.ApiResponse;
import com.java_ne.services.interfaces.AuthenticationService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*", methods = {RequestMethod.GET, RequestMethod.POST, RequestMethod.PUT, RequestMethod.DELETE, RequestMethod.PATCH, RequestMethod.OPTIONS})
@RequestMapping(path = "/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    public ResponseEntity<ApiResponse<AuthResponse>> login(@Valid @RequestBody LoginDTO signInDTO) {
        return authenticationService.login(signInDTO);
    }

    @PostMapping("/register")
    public ResponseEntity<ApiResponse<AuthResponse>> register(@Valid @RequestBody RegisterUserDTO registerUserDTO) {
        return authenticationService.register(registerUserDTO);
    }
}
