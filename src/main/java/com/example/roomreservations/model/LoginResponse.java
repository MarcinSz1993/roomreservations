package com.example.roomreservations.model;

import com.example.roomreservations.config.JwtIssuer;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Builder
public class LoginResponse {
    private final String accessToken;
}
