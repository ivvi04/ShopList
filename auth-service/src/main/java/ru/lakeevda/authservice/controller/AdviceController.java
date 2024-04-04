package ru.lakeevda.authservice.controller;

import org.springframework.http.HttpStatus;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.lakeevda.authservice.exception.ConfirmPasswordIncorrectException;
import ru.lakeevda.authservice.exception.OldPasswordIncorrectException;
import ru.lakeevda.authservice.exception.UserEmailExistException;
import ru.lakeevda.authservice.exception.UserPhoneExistException;

@RestControllerAdvice
public class AdviceController {

    @ExceptionHandler(UsernameNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String userNotAuthor(UsernameNotFoundException e){
        return e.getMessage();
    }

    @ExceptionHandler(UserPhoneExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String dataNotFound(UserPhoneExistException e){
        return e.getMessage();
    }

    @ExceptionHandler(UserEmailExistException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String dataNotFound(UserEmailExistException e){
        return e.getMessage();
    }

    @ExceptionHandler(OldPasswordIncorrectException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String oldPasswordIncorrect(OldPasswordIncorrectException e){
        return e.getMessage();
    }

    @ExceptionHandler(ConfirmPasswordIncorrectException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String confirmPasswordIncorrect(ConfirmPasswordIncorrectException e){
        return e.getMessage();
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String otherException(RuntimeException e){
        return "Неизвестная ошибка: " + e.getMessage();
    }
}
