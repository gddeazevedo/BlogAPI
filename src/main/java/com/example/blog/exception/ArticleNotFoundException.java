package com.example.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class ArticleNotFoundException extends Exception {
    public ArticleNotFoundException() {}

    public ArticleNotFoundException(Long id) {
        super("Article with id = " + id + " was not found!");
    }
}
