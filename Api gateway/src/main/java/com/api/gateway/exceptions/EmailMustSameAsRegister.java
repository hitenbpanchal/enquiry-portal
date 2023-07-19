package com.api.gateway.exceptions;

public class EmailMustSameAsRegister extends RuntimeException {
    String fieldName;
    String fieldValue;
    public EmailMustSameAsRegister(String fieldName, String fieldValue) {
        super(String.format("User email must be same as register email %s : %s", fieldName,fieldValue ));
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }
}
