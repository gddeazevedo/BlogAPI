package com.example.blog.helper;

import com.example.blog.entity.Author;
import com.example.blog.exception.AuthorNotValidException;

import org.springframework.stereotype.Component;

@Component
public class AuthorHelper implements Helper<Author> {

    @Override
    public void raiseExceptionIfAttributesAreNotValid(Author author) throws AuthorNotValidException {
        if (isNameInvalid(author) && isEmailInvalid(author)) {
            throw new AuthorNotValidException("Name and Email are either null or empty, please provide an email and name!");
        }

        if (isNameInvalid(author)) {
            throw new AuthorNotValidException("Name is either empty or null. Plase provide a name!");
        }

        if (isEmailInvalid(author)) {
            throw new AuthorNotValidException("Email is either empty or null. Please provide an email!");
        }
    }

    private boolean isNameInvalid(Author author) {
        return "".equals(author.getName()) || author.getName() == null;
    }

    private boolean isEmailInvalid(Author author) {
        return "".equals(author.getEmail()) || author.getEmail() == null;
    }
}
