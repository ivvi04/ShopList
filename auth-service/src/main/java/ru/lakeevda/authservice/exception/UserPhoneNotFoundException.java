package ru.lakeevda.authservice.exception;

import org.springframework.security.core.AuthenticationException;

public class UserPhoneNotFoundException extends AuthenticationException {

    public UserPhoneNotFoundException(String msg) {
        super(msg);
    }

    public UserPhoneNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
