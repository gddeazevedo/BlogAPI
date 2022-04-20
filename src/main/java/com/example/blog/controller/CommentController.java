package com.example.blog.controller;

import java.util.List;

import com.example.blog.dto.request.CommentDTO;
import com.example.blog.exception.ArticleNotFoundException;
import com.example.blog.exception.AuthorNotFoundException;
import com.example.blog.exception.CommentNotFoundException;
import com.example.blog.exception.CommentNotValidException;
import com.example.blog.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService service;
    
    @GetMapping
    public List<CommentDTO> findAll(
        @RequestParam(
            required = false,
            name = "articleId"
        )
        Long articleId,

        @RequestParam(
            required = false,
            name = "authorId"
        )
        Long authorId
    )
    {

        if (articleId != null && authorId != null) {
            return service.findAllByArticleAndAuthor(articleId, authorId);
        }

        if (articleId != null) {
            return service.findAllByArticle(articleId);
        }

        if (authorId != null) {
            return service.findAllByAuthor(authorId);
        }

        return service.findAll();
    }

    @GetMapping("/{id}")
    public CommentDTO findBy(@PathVariable("id") Long id) throws CommentNotFoundException
    {
        return service.findBy(id);
    }

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
