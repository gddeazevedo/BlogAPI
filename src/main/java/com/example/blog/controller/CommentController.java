package com.example.blog.controller;

import com.example.blog.dto.request.CommentDTO;
import com.example.blog.exception.ArticleNotFoundException;
import com.example.blog.exception.AuthorNotFoundException;
import com.example.blog.exception.CommentNotFoundException;
import com.example.blog.exception.CommentNotValidException;
import com.example.blog.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService service;

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public CommentDTO create(
        @RequestBody CommentDTO commentDTO
    ) throws CommentNotValidException, AuthorNotFoundException, ArticleNotFoundException
    {
        return service.create(commentDTO);
    }

    @PutMapping("/{id}")
    public CommentDTO update(
        @PathVariable("id") Long id,
        @RequestBody CommentDTO commentDTO
    ) throws CommentNotFoundException, CommentNotValidException
    {
        return service.update(id, commentDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) throws CommentNotFoundException {
        service.delete(id);
    }

}
