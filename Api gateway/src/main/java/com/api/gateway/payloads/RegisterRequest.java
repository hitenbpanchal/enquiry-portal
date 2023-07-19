package com.api.gateway.payloads;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;
import lombok.Data;
@Data
public class RegisterRequest {
    @Email(message = "Email must be valid")
    @NotEmpty(message = "Email must not be empty")
    private String email;
    @NotEmpty(message = "Password must not be empty")
    @Size(min = 4, max = 30,message = "Password must be strong")
    private String password;
//    private Set<String> roles;
}
