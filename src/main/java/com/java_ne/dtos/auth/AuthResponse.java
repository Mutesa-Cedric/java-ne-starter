package com.java_ne.dtos.auth;

import com.java_ne.models.User;
import lombok.Data;

@Data
public class AuthResponse {
    private User user;
    private TokensResponse tokens;

    public AuthResponse(String accessToken, User user) {
        this.tokens = new TokensResponse(accessToken);
        this.user = user;
    }
}
