package com.api.gateway.controller;

import com.api.gateway.payloads.RegisterRequest;
import com.api.gateway.payloads.RegisterResponse;
import com.api.gateway.services.AuthService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;

@RestController
@RequestMapping("/register")
@RequiredArgsConstructor
@CrossOrigin("*")
public class registerController {

    private final AuthService authService;

    @PostMapping("/new")
    public ResponseEntity<RegisterResponse> register(@Valid @RequestBody RegisterRequest request) throws URISyntaxException {
        final RegisterResponse register = authService.register(request);
        return new ResponseEntity<>(register, HttpStatus.CREATED);
    }
}
