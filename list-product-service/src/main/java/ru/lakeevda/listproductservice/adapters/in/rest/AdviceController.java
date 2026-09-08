package ru.lakeevda.listproductservice.adapters.in.rest;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import ru.lakeevda.listproductservice.domain.exception.ProductNotFoundException;
import ru.lakeevda.listproductservice.domain.exception.UserNotAuthorException;
import ru.lakeevda.listproductservice.domain.exception.ListNotFoundException;
import ru.lakeevda.listproductservice.domain.exception.ProductExistException;


@RestControllerAdvice
public class AdviceController {
    @ExceptionHandler(UserNotAuthorException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public String userNotAuthor(UserNotAuthorException e) {
        return e.getMessage();
    }

    @ExceptionHandler(ListNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String listNotFound(ListNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(ProductNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public String dataNotFound(ProductNotFoundException e) {
        return e.getMessage();
    }

    @ExceptionHandler(ProductExistException.class)
    @ResponseStatus(HttpStatus.CONFLICT)
    public String productExist(ProductExistException e) {
        return e.getMessage();
    }

    @ExceptionHandler(RuntimeException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public String otherException(RuntimeException e) {
        return "Неизвестная ошибка: " + e.getMessage();
    }
}
