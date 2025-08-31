package com.example.demo.exception;

public class UserNotFoundException extends Exception {

    public UserNotFoundException(String errorMessage, Throwable err) {
        super(errorMessage, err);
    }
}
