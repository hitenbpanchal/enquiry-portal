package com.api.gateway.controller;

import com.api.gateway.payloads.CurrentUserResponse;
import com.api.gateway.payloads.LoginRequest;
import com.api.gateway.payloads.RegisterResponse;
import com.api.gateway.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
@CrossOrigin("*")
public class LoginController {

    private final RestTemplate restTemplate;

    private final AuthService authService;

    @PostMapping
    public ResponseEntity<RegisterResponse> loginUser(@Valid @RequestBody LoginRequest request) throws URISyntaxException {
        RegisterResponse login = authService.login(request);
        return new ResponseEntity<>(login,HttpStatus.CREATED);
    }
    @GetMapping("/current-user")
    public ResponseEntity<CurrentUserResponse> currentUser(@RequestHeader("Authorization") String token) throws URISyntaxException {
        CurrentUserResponse currentUser = authService.getCurrentUser(token);
        return ResponseEntity.ok(currentUser);
    }
}
