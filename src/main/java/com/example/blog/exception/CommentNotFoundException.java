package com.example.blog.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.NOT_FOUND)
public class CommentNotFoundException extends Exception {
    public CommentNotFoundException() {}

    public CommentNotFoundException(Long id) {
        super("Comment with id = " + id  + " was not found!");
    }
}
