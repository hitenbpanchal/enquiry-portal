package com.api.gateway.services.impl;

import com.api.gateway.exceptions.EmailMustSameAsRegister;
import com.api.gateway.exceptions.UserNotAuthenticatedException;
import com.api.gateway.exceptions.UserPresentException;
import com.api.gateway.payloads.*;
import com.api.gateway.services.AuthService;
import io.micrometer.observation.annotation.Observed;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;

@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {

    private final RestTemplate restTemplate;
    HttpHeaders headers = new HttpHeaders();
    @Observed(name = "Register.user")
    @Override
    public RegisterResponse register(RegisterRequest request) throws URISyntaxException {
        if (this.isUserPresent(request.getEmail())){
            throw new UserPresentException("Email", request.getEmail());
        }
        URI uri = new URI("http://JWT-TOKEN-GENERATOR/auth/register");
        return restTemplate.postForObject(uri, request, RegisterResponse.class);
    }
    @Observed(name = "Login.user")
    @Override
    public RegisterResponse login(LoginRequest request) throws URISyntaxException {
        URI uri = new URI("http://JWT-TOKEN-GENERATOR/auth/login");
        return restTemplate.postForObject(uri, request, RegisterResponse.class);
    }
    @Observed(name = "enquiry.submit")
    @Override
    public EnquiryForm enquiryForm(EnquiryForm request, String token) throws URISyntaxException {
        String reqToken = token.substring(7);
        AuthenticateUserResponse authenticatedUser = this.isAuthenticatedUser(reqToken);
        if(!authenticatedUser.getSuccess()){
            throw new UserNotAuthenticatedException();
        }
        if (!this.isUserPresent(request.getEmail())){
            throw new EmailMustSameAsRegister("Email",request.getEmail());
        }
        URI submitUri = new URI("http://ENQUIRY-FORM/enquiry/adddetails");
        return restTemplate.postForObject(submitUri, request, EnquiryForm.class);
    }

    @Override
    public Profile saveProfile(EnquiryForm request) throws URISyntaxException {

        Profile profile = new Profile();
        profile.setName(request.getName());
        profile.setPhoneNumber(request.getContactNo());
        profile.setQualification(request.getQualification());
        profile.setCaurceName(request.getCourseName());
        profile.setAddress(request.getAddress());
        URI submitUri = new URI("http://PROFILE-SERVICE/profile/new");
        return restTemplate.postForObject(submitUri, profile, Profile.class);
    }
    @Override
    public AuthenticateUserResponse isAuthenticatedUser(String token) throws URISyntaxException {
        URI uri = new URI("http://JWT-TOKEN-GENERATOR/auth/verify");
        return restTemplate.postForObject(uri, token,AuthenticateUserResponse.class);
    }

    @Override
    public CurrentUserResponse getCurrentUser(String token) throws URISyntaxException {
        headers.set("Authorization",token);
        HttpEntity<String> httpEntity = new HttpEntity<>("some body", headers);
//        isAuthenticatedUser(reqToken);
        URI uri = new URI("http://JWT-TOKEN-GENERATOR/auth/current-user");
        return restTemplate.exchange(uri, HttpMethod.GET, httpEntity, CurrentUserResponse.class).getBody();
//        return restTemplate.getForObject(uri,CurrentUserResponse.class);
    }

    @Override
    public Boolean isUserPresent(String email) throws URISyntaxException {
        URI uri = new URI("http://JWT-TOKEN-GENERATOR/auth/is-present");
        return restTemplate.postForObject(uri,email,Boolean.class);
    }
}
