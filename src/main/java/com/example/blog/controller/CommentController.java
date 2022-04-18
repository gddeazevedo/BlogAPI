package com.example.blog.controller;

import java.util.List;

import com.example.blog.entity.Comment;
import com.example.blog.exception.ArticleNotFoundException;
import com.example.blog.exception.AuthorNotFoundException;
import com.example.blog.exception.CommentNotFoundException;
import com.example.blog.exception.CommentNotValidException;
import com.example.blog.service.CommentService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/comments")
public class CommentController {

    @Autowired
    private CommentService service;
    
    @GetMapping
    public List<Comment> findAll(
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
    public Comment findBy(@PathVariable("id") Long id) throws CommentNotFoundException
    {
        return service.findBy(id);
    }

    @PostMapping("/article/{articleId}/author/{authorId}")
    public Comment create(
        @PathVariable("articleId") Long articleId,
        @PathVariable("authorId") Long authorId,
        @RequestBody Comment comment
    ) throws CommentNotValidException, AuthorNotFoundException, ArticleNotFoundException
    {
        return service.create(articleId, authorId, comment);
    }

    @PutMapping("/{id}")
    public Comment update(
        @PathVariable("id") Long id,
        @RequestBody Comment comment
    ) throws CommentNotFoundException, CommentNotValidException
    {
        return service.update(id, comment);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) throws CommentNotFoundException {
        service.delete(id);
    }

}
