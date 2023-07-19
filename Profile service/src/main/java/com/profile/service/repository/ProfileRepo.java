package com.profile.service.repository;

import com.profile.service.entities.Profile;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProfileRepo extends JpaRepository<Profile, Integer> {
    Profile findByName(String name);
    Profile findByPhoneNumber(String phoneNumber);
}
