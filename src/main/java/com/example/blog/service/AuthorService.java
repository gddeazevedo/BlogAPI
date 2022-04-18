package com.example.blog.service;

import com.example.blog.repository.AuthorRepository;
import com.example.blog.entity.Author;
import com.example.blog.exception.AuthorNotFoundException;
import com.example.blog.exception.AuthorNotValidException;
import com.example.blog.exception.EmailAlreadyTakenException;
import com.example.blog.helper.AuthorHelper;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class AuthorService {
    
    @Autowired
    private AuthorRepository repository;

    @Autowired
    private AuthorHelper helper;

    public List<Author> findAll() {
        return repository.findAll();
    }

    public Author findBy(Long id) throws AuthorNotFoundException {
        return repository.findById(id).orElseThrow(() ->
            new AuthorNotFoundException(id)
        );
    }

    public Author create(Author author) throws EmailAlreadyTakenException, AuthorNotValidException {
        raiseExceptionIfEmailIsTaken(author.getEmail());
        helper.raiseExceptionIfAttributesAreNotValid(author);
        return repository.save(author);
    }

    public Author update(Long id, Author author) throws AuthorNotFoundException, AuthorNotValidException {
        Author authorToUpdate = repository.findById(id).orElseThrow(() ->
            new AuthorNotFoundException()
        );

        helper.raiseExceptionIfAttributesAreNotValid(author);

        authorToUpdate.setName(author.getName());
        authorToUpdate.setEmail(author.getEmail());

        return repository.save(authorToUpdate);
    }

    public void delete(Long id) throws AuthorNotFoundException {
        Author author = repository.findById(id).orElseThrow(() ->
            new AuthorNotFoundException(id)
        );

        repository.delete(author);
    }

    private void raiseExceptionIfEmailIsTaken(String email) throws EmailAlreadyTakenException{
        Optional<Author> optionalAuthor = repository.findByEmail(email);

        if (optionalAuthor.isPresent()) {
            throw new EmailAlreadyTakenException(email);
        }
    }
}
