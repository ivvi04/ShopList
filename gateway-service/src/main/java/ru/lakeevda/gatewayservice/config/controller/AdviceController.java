package ru.lakeevda.gatewayservice.config.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.lakeevda.gatewayservice.exception.MissingAuthHeaderException;
import ru.lakeevda.gatewayservice.exception.UnAuthAccessToAppException;


@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(MissingAuthHeaderException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String missingAuthHeader(MissingAuthHeaderException e){
        return e.getMessage();
    }

    @ExceptionHandler(UnAuthAccessToAppException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public String unAuthAccessToApp(UnAuthAccessToAppException e){
        return e.getMessage();
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String otherException(RuntimeException e){
        return "Неизвестная ошибка: " + e.getMessage();
    }
}
