package com.api.gateway.services.impl;

import com.api.gateway.exceptions.UserNotAuthenticatedException;
import com.api.gateway.payloads.AuthenticateUserResponse;
import com.api.gateway.payloads.Profile;
import com.api.gateway.services.AuthService;
import com.api.gateway.services.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final RestTemplate restTemplate;
    private final AuthService authService;
    @Override
    public List<Profile> getAllProfiles(String token) throws URISyntaxException {
        String reqToken = token.substring(7);
        AuthenticateUserResponse authenticatedUser = authService.isAuthenticatedUser(reqToken);
        if (!authenticatedUser.getSuccess()){
            throw new UserNotAuthenticatedException();
        }
        URI getUri = new URI("http://PROFILE-SERVICE/profile/all");
        Profile[] response = restTemplate.getForObject(getUri, Profile[].class);
        assert response != null;
        return Arrays.stream(response).collect(Collectors.toList());
    }

    @Override
    public Profile getAllProfilesByName(String token,String name) throws URISyntaxException {
        String reqToken = token.substring(7);
        AuthenticateUserResponse authenticatedUser = authService.isAuthenticatedUser(reqToken);
        if (!authenticatedUser.getSuccess()){
            throw new UserNotAuthenticatedException();
        }
        URI getUri = new URI("http://PROFILE-SERVICE/profile/find/"+name);
        return restTemplate.getForObject(getUri, Profile.class);
    }

    @Override
    public Profile findByPhoneNumber(String phone, String token) throws URISyntaxException {
        String reqToken = token.substring(7);
        AuthenticateUserResponse authenticatedUser = authService.isAuthenticatedUser(reqToken);
        if (!authenticatedUser.getSuccess()){
            throw new UserNotAuthenticatedException();
        }
        authService.isAuthenticatedUser(reqToken);
        URI getUri = new URI("http://PROFILE-SERVICE/profile/phone/"+phone);
        return restTemplate.getForObject(getUri,Profile.class);
    }

    @Override
    public Profile newProfile(Profile profile, String token) throws URISyntaxException {
        String reqToken = token.substring(7);
        this.authService.isAuthenticatedUser(reqToken);
        URI postUri = new URI("http://PROFILE-SERVICE/profile/new");
        return restTemplate.postForObject(postUri,profile,Profile.class);
    }
}
