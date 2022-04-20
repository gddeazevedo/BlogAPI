package com.example.blog.helper;

import com.example.blog.dto.request.CommentDTO;
import com.example.blog.exception.CommentNotValidException;

import org.springframework.stereotype.Component;

@Component
public class CommentHelper implements Helper<CommentDTO> {

    @Override
    public void raiseExceptionIfAttributesAreNotValid(CommentDTO commentDTO) throws CommentNotValidException {
        if (isBodyInvalid(commentDTO.getBody())) {
            throw new CommentNotValidException("Body is null or empty! Please provide a body!");
        }
    }

    private boolean isBodyInvalid(String body) {
        return "".equals(body) || body == null;
    }
    
}
