package com.profile.service.controller;

import com.profile.service.entities.Profile;
import com.profile.service.service.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;
    @PostMapping("/new")
    public ResponseEntity<Profile> createProfile(@RequestBody Profile profile){
        Profile newProfile = profileService.cteateProfile(profile);
        return new ResponseEntity<>(newProfile, HttpStatus.CREATED);
    }

    @GetMapping("/{profileId}")
    public ResponseEntity<Profile> getSingleProfile(@PathVariable Integer profileId){
        Profile Profile = profileService.getProfile(profileId);
        return new ResponseEntity<>(Profile, HttpStatus.OK);
    }
    @GetMapping("/find/{name}")
    public ResponseEntity<Profile> getProfileByName(@PathVariable String name){
        return ResponseEntity.ok(profileService.getByName(name));
    }

    @GetMapping("/all")
    public ResponseEntity<List<Profile>> getAllProfile(){
        List<Profile> Profiles = (List<Profile>) profileService.getAllProfiles();
        return ResponseEntity.ok(Profiles);
    }

    @PutMapping("/{profileId}")
    public ResponseEntity<Profile> getSingleProfile(@PathVariable Integer profileId,@RequestBody Profile profile){
        Profile Profile = profileService.getProfile(profileId);
        return new ResponseEntity<>(Profile, HttpStatus.OK);
    }
    @DeleteMapping("/{profileId}")
    public ResponseEntity<String> deleteProfile(@PathVariable Integer profileId){
        profileService.deleteProfile(profileId);
        return new ResponseEntity<>("Profile deleted successfully", HttpStatus.OK);
    }
    @GetMapping("/phone/{phoneNumber}")
    public ResponseEntity<Profile> findByPhone(@PathVariable String phoneNumber){
        return new ResponseEntity<>(this.profileService.findByPhone(phoneNumber),HttpStatus.OK);
    }
}
