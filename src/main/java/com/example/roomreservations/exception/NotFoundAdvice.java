package com.example.roomreservations.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class NotFoundAdvice {
@ResponseBody
@ExceptionHandler(GuestNotFoundException.class)
@ResponseStatus(HttpStatus.NOT_FOUND)
public String GuestNotFoundHandler(GuestNotFoundException ex){
    return ex.getMessage();
}

}
