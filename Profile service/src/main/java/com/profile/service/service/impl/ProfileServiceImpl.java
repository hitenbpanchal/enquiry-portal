package com.profile.service.service.impl;

import com.profile.service.entities.Profile;
import com.profile.service.repository.ProfileRepo;
import com.profile.service.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Collection;

@Service
@RequiredArgsConstructor
public class ProfileServiceImpl implements ProfileService {

    private final ProfileRepo profileRepo;
//    Logger logger = Logger.getLogger(ProfileServiceImpl.class);

    @Override
    public Profile cteateProfile(Profile profile) {
        return profileRepo.save(profile);
    }

    @Override
    public Profile getProfile(Integer id) {
        return profileRepo.findById(id).orElseThrow(()-> new IllegalStateException("Profile not found"));
    }

    @Override
    public Profile getByName(String name) {
        return profileRepo.findByName(name);
    }

    @Override
    public Collection<Profile> getAllProfiles() {
       return profileRepo.findAll();
    }

    @Override
    public Profile updateProfile(Integer id, Profile profile) {
        Profile findProfile = profileRepo.findById(id).orElseThrow(() -> new IllegalStateException("Couldn't update profile'"));
        findProfile.setName(profile.getName());
        findProfile.setAddress(profile.getAddress());
        findProfile.setQualification(profile.getQualification());
        findProfile.setCaurceName(profile.getCaurceName());
        findProfile.setPhoneNumber(profile.getPhoneNumber());
        return profileRepo.save(findProfile);
    }

    @Override
    public void deleteProfile(Integer id) {
        Profile findProfile = profileRepo.findById(id).orElseThrow(() -> new IllegalStateException("Couldn't update profile'"));
        profileRepo.delete(findProfile);
    }

    @Override
    public Profile findByPhone(String phone) {
        return this.profileRepo.findByPhoneNumber(phone);
    }
}
