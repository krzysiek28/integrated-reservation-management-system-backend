package com.uliasz.irms.backend.rest.objects.response;

import lombok.Data;

@Data
public class JwtResponse {
    private String token;
    private String type = "Bearer";
    private Long id;
    private String userName;
    private String email;
    private String role;

    public JwtResponse(String accessToken, Long id, String userName, String email, String role) {
        this.token = accessToken;
        this.id = id;
        this.userName = userName;
        this.email = email;
        this.role = role;
    }
}
