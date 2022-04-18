package com.example.blog.helper;

import com.example.blog.entity.Comment;
import com.example.blog.exception.CommentNotValidException;

import org.springframework.stereotype.Component;

@Component
public class CommentHelper implements Helper<Comment> {

    @Override
    public void raiseExceptionIfAttributesAreNotValid(Comment comment) throws CommentNotValidException {
        if (isBodyInvalid(comment.getBody())) {
            throw new CommentNotValidException("Body is null or empty! Please provide a body!");
        }
    }

    private boolean isBodyInvalid(String body) {
        return "".equals(body) || body == null;
    }
    
}
