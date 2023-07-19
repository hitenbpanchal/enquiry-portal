package com.api.gateway.exceptions;

public class UserNotAuthenticatedException extends RuntimeException {
    public UserNotAuthenticatedException(){
        super("User not authenticated");
    }
}
