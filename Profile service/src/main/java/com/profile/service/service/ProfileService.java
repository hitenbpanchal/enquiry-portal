package com.profile.service.service;

import com.profile.service.entities.Profile;

import java.util.Collection;

public interface ProfileService {

    Profile cteateProfile(Profile profile);

    Profile getProfile(Integer id);

    Profile getByName(String name);

    Collection<Profile> getAllProfiles();

    Profile updateProfile(Integer id,Profile profile);

    void deleteProfile(Integer id);

    Profile findByPhone(String phone);
}
