package com.example.blog.controller;

import java.util.List;

import com.example.blog.dto.request.AuthorDTO;
import com.example.blog.exception.AuthorNotFoundException;
import com.example.blog.exception.AuthorNotValidException;
import com.example.blog.exception.EmailAlreadyTakenException;
import com.example.blog.service.AuthorService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/authors")
public class AuthorController {
    
    @Autowired
    private AuthorService service;

    @GetMapping
    public List<AuthorDTO> findAll() {
        return service.findAll();
    }

    @GetMapping("/{id}")
    public AuthorDTO findBy(@PathVariable("id") Long id) throws AuthorNotFoundException {
        return service.findBy(id);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public AuthorDTO create(@RequestBody AuthorDTO authorDTO) throws EmailAlreadyTakenException, AuthorNotValidException {
        return service.create(authorDTO);
    }

    @PutMapping("/{id}")
    public AuthorDTO update(
        @PathVariable("id") Long id,
        @RequestBody AuthorDTO authorDTO
    ) throws AuthorNotFoundException, AuthorNotValidException
    {
        return service.update(id, authorDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) throws AuthorNotFoundException {
        service.delete(id);
    }
}
