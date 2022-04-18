package com.example.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class AuthorNotFoundException  extends Exception {
    public AuthorNotFoundException() {}

    public AuthorNotFoundException(Long id) {
        super("Author with id = " + id + " was not found!");
    }
}
