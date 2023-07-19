package com.api.gateway.controller;

import com.api.gateway.externalservices.RegisterFormService;
import com.api.gateway.payloads.EnquiryForm;
import com.api.gateway.payloads.Profile;
import com.api.gateway.services.AuthService;
import com.api.gateway.services.EnquiryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.net.URISyntaxException;
import java.util.List;
@CrossOrigin("*")
@RestController
@RequestMapping("/forms")
@RequiredArgsConstructor
public class EnquiryFormController {
    private final RegisterFormService registerFormService;

    private final AuthService authService;

    private final EnquiryService enquiryService;


//
//    private final JwtUtil jwtUtil;
    @CrossOrigin(origins = "http://localhost:4200")
    @PostMapping("/enq")
    public ResponseEntity<EnquiryForm> submitForm(@Valid @RequestBody EnquiryForm requestForm, @RequestHeader("Authorization") String token) throws URISyntaxException {
        EnquiryForm enquiryForm = authService.enquiryForm(requestForm, token);
        Profile profile = authService.saveProfile(requestForm);
        return new ResponseEntity<>(enquiryForm,HttpStatus.CREATED);
    }
    @PostMapping("/new")
    public ResponseEntity<EnquiryForm> newForm(@RequestBody EnquiryForm request){
        EnquiryForm requestForm = registerFormService.newForm(request);
        return new ResponseEntity<>(requestForm, HttpStatus.CREATED);
    }
    @GetMapping("/all")
    public ResponseEntity<List<EnquiryForm>> getALlEnquiry(@RequestHeader("Authorization") String token) throws URISyntaxException {
       return ResponseEntity.ok(enquiryService.getAllEnquiries(token));
    }
    @GetMapping("/find/{name}")
    public ResponseEntity<List<EnquiryForm>> getAllEnquiriesByName(@PathVariable String name,@RequestHeader("Authorization") String token) throws URISyntaxException {
        return ResponseEntity.ok(enquiryService.getAllEnquiriesByName(name, token));
    }

}
