package com.example.blog.exception;

import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.http.HttpStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class EmailAlreadyTakenException extends Exception {
    public EmailAlreadyTakenException() {}

    public EmailAlreadyTakenException(String email) {
        super("Email '" + email + "' is already in use! Please, use another email!");
    }
}
