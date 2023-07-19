package com.api.gateway.services;

import com.api.gateway.payloads.EnquiryForm;

import java.net.URISyntaxException;
import java.util.List;

public interface EnquiryService {

    List<EnquiryForm> getAllEnquiries(String token) throws URISyntaxException;

    List<EnquiryForm> getAllEnquiriesByName(String name,String token) throws URISyntaxException;
}
