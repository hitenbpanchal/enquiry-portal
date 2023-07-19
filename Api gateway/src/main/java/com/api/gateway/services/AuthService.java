package com.api.gateway.services;

import com.api.gateway.payloads.*;

import java.net.URISyntaxException;

public interface AuthService {

    RegisterResponse register(RegisterRequest request) throws URISyntaxException;

    RegisterResponse login(LoginRequest request) throws URISyntaxException;

    EnquiryForm enquiryForm(EnquiryForm request, String token) throws URISyntaxException;

    Profile saveProfile(EnquiryForm request) throws URISyntaxException;

    AuthenticateUserResponse isAuthenticatedUser(String token) throws URISyntaxException;

    CurrentUserResponse getCurrentUser(String token) throws URISyntaxException;

    Boolean isUserPresent(String email) throws URISyntaxException;


}
