package com.example.blog.controller;

import java.util.List;

import com.example.blog.entity.Author;
import com.example.blog.exception.AuthorNotFoundException;
import com.example.blog.exception.AuthorNotValidException;
import com.example.blog.exception.EmailAlreadyTakenException;
import com.example.blog.service.AuthorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    
    @Autowired
    private AuthorService service;

    @GetMapping
    public List<Author> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public Author findBy(@PathVariable("id") Long id) throws AuthorNotFoundException {
        return service.findBy(id);
    }

    @PostMapping
    public Author create(@RequestBody Author author) throws EmailAlreadyTakenException, AuthorNotValidException {
        return service.create(author);
    }

    @PutMapping("/{id}")
    public Author update(
        @PathVariable("id") Long id,
        @RequestBody Author author
    ) throws AuthorNotFoundException, AuthorNotValidException
    {
        return service.update(id, author);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) throws AuthorNotFoundException {
        service.delete(id);
    }
}
