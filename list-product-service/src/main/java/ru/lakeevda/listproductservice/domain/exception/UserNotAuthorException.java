package ru.lakeevda.listproductservice.domain.exception;

public class UserNotAuthorException extends RuntimeException {
    public UserNotAuthorException() {
        super("Только у автора есть права на редактирование/удаление");
    }
}
