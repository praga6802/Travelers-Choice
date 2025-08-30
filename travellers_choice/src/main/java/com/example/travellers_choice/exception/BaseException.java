package com.example.travellers_choice.exception;

import org.springframework.http.HttpStatus;

import java.util.prefs.BackingStoreException;

public class BaseException extends RuntimeException{

    private final HttpStatus status;
    public BaseException(String message, HttpStatus status){
        super(message);
        this.status=status;
    }

    public HttpStatus getStatus(){
        return status;
    }
}
