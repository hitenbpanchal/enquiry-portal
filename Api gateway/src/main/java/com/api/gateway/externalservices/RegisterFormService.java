package com.api.gateway.externalservices;

import com.api.gateway.payloads.EnquiryForm;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
@FeignClient(name = "ENQUIRY-FORM")
public interface RegisterFormService {
    @PostMapping("/enquiry/adddetails")
    EnquiryForm newForm(@RequestBody EnquiryForm requestForm);
}
