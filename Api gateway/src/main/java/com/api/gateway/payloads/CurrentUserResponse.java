package com.api.gateway.payloads;

import lombok.Data;

import java.util.List;
@Data
public class CurrentUserResponse {

    private String password;
    private String username;
    private List<Authorities> authorities;
    private Boolean accountNonExpired;
    private Boolean accountNonLocked;
    private Boolean credentialsNonExpired;
    private Boolean enabled;
}
