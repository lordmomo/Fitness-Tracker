package com.momo.fitnessTracker.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    public String  userNotFoundException(UserNotFoundException e){
        return e.getMessage();
    }
}
