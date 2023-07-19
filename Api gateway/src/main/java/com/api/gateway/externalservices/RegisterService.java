package com.api.gateway.externalservices;

import com.api.gateway.payloads.RegisterRequest;
import com.api.gateway.payloads.RegisterResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name = "JWT-TOKEN-GENERATOR")
public interface RegisterService {
    @PostMapping("/auth/register")
    RegisterResponse newRegister(@RequestBody RegisterRequest request);
}
