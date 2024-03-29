package ru.lakeevda.userservice.controller;

import jakarta.ws.rs.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.lakeevda.userservice.exception.DataNotFoundException;
import ru.lakeevda.userservice.exception.OtherUserExistException;
import ru.lakeevda.userservice.exception.UserExistException;


@RestControllerAdvice
public class AdviceController {
    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String dataNotFound(DataNotFoundException e){
        return e.getMessage();
    }

    @ExceptionHandler(UserExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String userExist(UserExistException e){
        return e.getMessage();
    }

    @ExceptionHandler(OtherUserExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String otherUserExist(OtherUserExistException e){
        return e.getMessage();
    }
}
