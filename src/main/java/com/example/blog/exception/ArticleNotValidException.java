package com.example.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_ACCEPTABLE)
public class ArticleNotValidException extends Exception {
    public ArticleNotValidException() {}

    public ArticleNotValidException(String message) {
        super(message);
    }
}
