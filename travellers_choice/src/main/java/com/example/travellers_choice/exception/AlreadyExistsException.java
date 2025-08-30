package com.example.travellers_choice.exception;

import org.springframework.http.HttpStatus;

public class AlreadyExistsException extends BaseException{

    public AlreadyExistsException(String fieldName, String value){
        super(fieldName +" '"+value+"' "+" already Exists!", HttpStatus.CONFLICT);
    }
}