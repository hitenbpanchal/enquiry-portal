package com.api.gateway.exceptions;

public class UserPresentException extends RuntimeException{
    String fieldName;
    String fieldValue;
    public UserPresentException(String fieldName, String fieldValue) {
        super(String.format("User is already present with %s : %s", fieldName,fieldValue ));
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
