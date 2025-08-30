package com.example.travellers_choice.exception;

import org.springframework.http.HttpStatus;

public class IDNotFoundException extends BaseException {
    public IDNotFoundException(String field,int id) {
        super(field +" '"+id+"' "+" not found", HttpStatus.NOT_FOUND);
    }
}
