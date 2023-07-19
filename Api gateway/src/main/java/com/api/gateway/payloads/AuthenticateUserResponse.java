package com.api.gateway.payloads;

import lombok.Data;

@Data
public class AuthenticateUserResponse {
    private String message;
    private Boolean success;
}
