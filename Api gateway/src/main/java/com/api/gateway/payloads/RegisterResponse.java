package com.api.gateway.payloads;

import lombok.Data;

@Data
public class RegisterResponse {

    private String token;
    private String uuId;
    private String email;
}
