package com.api.gateway.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class LoginRequest {
    @Email(message = "Email is mandatory")
    private String username;
    @NotEmpty(message = "Password is mandatory")
    @Size(min = 4, max = 30,message = "Password must be strong")
    private String password;
}
