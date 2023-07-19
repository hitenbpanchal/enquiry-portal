package com.api.gateway.services.impl;

import com.api.gateway.exceptions.UserNotAuthenticatedException;
import com.api.gateway.payloads.AuthenticateUserResponse;
import com.api.gateway.payloads.EnquiryForm;
import com.api.gateway.services.AuthService;
import com.api.gateway.services.EnquiryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EnquiryServiceImpl implements EnquiryService {

    private final RestTemplate restTemplate;

    private final AuthService authService;

    @Override
    public List<EnquiryForm> getAllEnquiries(String token) throws URISyntaxException {
        String reqToken = token.substring(7);
        AuthenticateUserResponse authenticatedUser = authService.isAuthenticatedUser(reqToken);
        if (!authenticatedUser.getSuccess()){
            throw new UserNotAuthenticatedException();
        }
        URI getUri = new URI("http://ENQUIRY-FORM/enquiry/all");
        EnquiryForm[] results = restTemplate.getForObject(getUri, EnquiryForm[].class);
        assert results != null;
         return Arrays.stream(results).toList();
    }

    @Override
    public List<EnquiryForm> getAllEnquiriesByName(String name,String token) throws URISyntaxException {
        String reqToken = token.substring(7);
        AuthenticateUserResponse authenticatedUser = authService.isAuthenticatedUser(reqToken);
        if (!authenticatedUser.getSuccess()){
            throw new UserNotAuthenticatedException();
        }
        URI getUri = new URI("http://ENQUIRY-FORM/enquiry/find/"+name);
        EnquiryForm[] responseArr = restTemplate.getForObject(getUri, EnquiryForm[].class);
        assert responseArr != null;
        return Arrays.stream(responseArr).toList();
    }
}
