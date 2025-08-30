package com.example.travellers_choice.exception;


import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.Map;
import java.util.prefs.BackingStoreException;

@RestControllerAdvice
public class GlobalExceptionaHandler {

    @ExceptionHandler(BaseException.class)
    public ResponseEntity<Map<String,String>> handleCustomException(BaseException b){
        return ResponseEntity.status(b.getStatus()).body(Map.of("error",b.getMessage()));
    }


    @ExceptionHandler(Exception.class)
    public ResponseEntity<Map<String,String>> handleException(Exception e){
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(Map.of("error","Something went wrong", "details",e.getMessage()));
    }

}
