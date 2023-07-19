package com.organizemanagemodule.exceptions;

public class UnAuthenticatedUser extends RuntimeException{
    public UnAuthenticatedUser(){
        super("User is not authenticated");
    }
}
