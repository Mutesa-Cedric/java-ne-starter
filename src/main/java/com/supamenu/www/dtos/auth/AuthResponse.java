package com.supamenu.www.dtos.auth;

import com.supamenu.www.models.User;
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
