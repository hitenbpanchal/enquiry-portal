package com.api.gateway.controller;

import com.api.gateway.externalservices.RegisterService;
import com.api.gateway.payloads.RegisterRequest;
import com.api.gateway.payloads.RegisterResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/regi")
public class FeinRegister {
    @Autowired
    private RegisterService registerService;
    @PostMapping("/new")
    public ResponseEntity<RegisterResponse> newRegister(@RequestBody RegisterRequest request){
        RegisterResponse response = registerService.newRegister(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
