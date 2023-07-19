package com.api.gateway.services;

import com.api.gateway.payloads.Profile;

import java.net.URISyntaxException;
import java.util.List;

public interface ProfileService {

    List<Profile> getAllProfiles(String token) throws URISyntaxException;
    Profile getAllProfilesByName(String name,String token) throws URISyntaxException;
    Profile findByPhoneNumber(String name,String token) throws URISyntaxException;
    Profile newProfile(Profile profile,String token) throws URISyntaxException;
}
