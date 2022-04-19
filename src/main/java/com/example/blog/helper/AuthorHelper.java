package com.example.blog.helper;

import com.example.blog.dto.request.AuthorDTO;
import com.example.blog.exception.AuthorNotValidException;

import org.springframework.stereotype.Component;

@Component
public class AuthorHelper implements Helper<AuthorDTO> {

    @Override
    public void raiseExceptionIfAttributesAreNotValid(AuthorDTO authorDTO) throws AuthorNotValidException {
        if (isNameInvalid(authorDTO.getName()) && isEmailInvalid(authorDTO.getEmail())) {
            throw new AuthorNotValidException("Name and Email are either null or empty, please provide an email and name!");
        }

        if (isNameInvalid(authorDTO.getName())) {
            throw new AuthorNotValidException("Name is either empty or null. Plase provide a name!");
        }

        if (isEmailInvalid(authorDTO.getEmail())) {
            throw new AuthorNotValidException("Email is either empty or null. Please provide an email!");
        }
    }

    private boolean isNameInvalid(String name) {
        return "".equals(name) || name == null;
    }

    private boolean isEmailInvalid(String email) {
        return "".equals(email) || email == null;
    }
}
