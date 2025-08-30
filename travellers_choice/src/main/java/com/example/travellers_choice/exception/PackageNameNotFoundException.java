package com.example.travellers_choice.exception;

import org.springframework.http.HttpStatus;

public class PackageNameNotFoundException extends BaseException {
    public PackageNameNotFoundException(String fieldName, String value) {
        super(fieldName+" '"+value+"' "+" not found", HttpStatus.NOT_FOUND);
    }
}
