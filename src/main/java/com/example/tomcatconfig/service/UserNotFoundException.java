package com.example.tomcatconfig.service;

public class UserNotFoundException extends Exception{
    public UserNotFoundException(String message) {
        super( message );
    }
}
