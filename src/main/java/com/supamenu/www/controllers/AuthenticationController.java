package com.supamenu.www.controllers;

import com.supamenu.www.dtos.auth.RegisterUserDTO;
import com.supamenu.www.dtos.response.ApiResponse;
import com.supamenu.www.services.interfaces.AuthenticationService;
import com.supamenu.www.dtos.auth.AuthResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.supamenu.www.dtos.auth.LoginDTO;

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
