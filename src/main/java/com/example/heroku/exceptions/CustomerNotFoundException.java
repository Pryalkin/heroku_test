package com.example.heroku.exceptions;

public class CustomerNotFoundException extends Exception{
    public CustomerNotFoundException(String message) {
        super(message);
    }
}
