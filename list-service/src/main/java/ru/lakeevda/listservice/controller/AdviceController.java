package ru.lakeevda.listservice.controller;

import jakarta.ws.rs.NotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.lakeevda.listservice.exception.DataNotFoundException;
import ru.lakeevda.listservice.exception.UserNotAuthorException;


@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(UserNotAuthorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String userNotAuthor(UserNotAuthorException e){
        return e.getMessage();
    }

    @ExceptionHandler(DataNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String dataNotFound(DataNotFoundException e){
        return e.getMessage();
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String otherException(RuntimeException e){
        return "Неизвестная ошибка: " + e.getMessage();
    }
}
