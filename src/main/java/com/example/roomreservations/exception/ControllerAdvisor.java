package com.example.roomreservations.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class ControllerAdvisor {
@ResponseBody
@ExceptionHandler(GuestNotFoundException.class)
@ResponseStatus(HttpStatus.NOT_FOUND)
public String GuestNotFoundHandler(GuestNotFoundException ex){
    return ex.getMessage();
}
@ResponseBody
@ExceptionHandler(DatesNotAvailableException.class)
@ResponseStatus(HttpStatus.CONFLICT)
public String DatesNotAvailableHandler(DatesNotAvailableException ex){
    return ex.getMessage();
}
@ResponseBody
@ExceptionHandler
@ResponseStatus(HttpStatus.BAD_REQUEST)
public String WrongDatesHandler(WrongDatesException ex){
    return ex.getMessage();
}
@ResponseBody
@ExceptionHandler
@ResponseStatus(HttpStatus.NOT_FOUND)
public String RoomExceptionHandler(RoomException ex){
    return ex.getMessage();
}

}
