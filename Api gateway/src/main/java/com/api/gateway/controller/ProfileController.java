package com.api.gateway.controller;

import com.api.gateway.payloads.Profile;
import com.api.gateway.services.ProfileService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URISyntaxException;
import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/profile")
@RequiredArgsConstructor
public class ProfileController {

    private final ProfileService profileService;
    @GetMapping("/all")
    public ResponseEntity<List<Profile>> getAllProfiles(@RequestHeader("Authorization") String token) throws URISyntaxException {
        return ResponseEntity.ok(profileService.getAllProfiles(token));
    }
    @GetMapping("/findApi/{name}")
    public ResponseEntity<Profile> getProfilesByName(@RequestHeader("Authorization") String token, @PathVariable String name) throws URISyntaxException{
        return ResponseEntity.ok(profileService.getAllProfilesByName(token, name));
    }
    @GetMapping("/phoneApi/{phone}")
    public ResponseEntity<Profile> getProfileByPhone(@PathVariable String phone,@RequestHeader("Authorization") String token) throws URISyntaxException{
        return ResponseEntity.ok(profileService.findByPhoneNumber(phone, token));
    }
    @PostMapping("/newProfile")
    public ResponseEntity<Profile> registerProfile(@RequestBody Profile profile,@RequestHeader("Authorization") String token) throws URISyntaxException{
        return new ResponseEntity<>(profileService.newProfile(profile, token), HttpStatus.CREATED);
    }

}
