package com.api.gateway.payloads;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class VerifyResponse {

    private String uuId;

    private String email;

    private Object authorities;

    private Boolean isVerify;
}
