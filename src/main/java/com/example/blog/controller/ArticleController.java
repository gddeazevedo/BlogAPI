package com.example.blog.controller;

import com.example.blog.exception.AuthorNotFoundException;
import com.example.blog.service.ArticleService;
import com.example.blog.entity.Article;
import com.example.blog.exception.ArticleNotFoundException;
import com.example.blog.exception.ArticleNotValidException;

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

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    
    @Autowired
    private ArticleService service;

    @GetMapping
    public List<Article> findAll(
        @RequestParam(
            required = false,
            name = "title"
        )
        String title
    ) {
        if (title != null) {
            return service.findBy(title);
        }

        return service.findAll();
    }

    @GetMapping("/{id}")
    public Article findBy(@PathVariable("id") Long id) throws ArticleNotFoundException {
        return service.findBy(id);
    }

    @GetMapping("/author/{author_id}")
    public List<Article> findByAuthor(@PathVariable("author_id") Long authorId) {
        return service.findByAuthor(authorId);
    }

    @PostMapping("/author/{author_id}")
    public Article create(
        @PathVariable("author_id") Long authorId,
        @RequestBody Article article
    ) throws ArticleNotValidException, AuthorNotFoundException {
        return service.create(authorId, article);
    }

    @PutMapping("/{id}")
    public Article update(
        @PathVariable("id") Long id,
        @RequestBody Article article
    ) throws ArticleNotFoundException, ArticleNotValidException
    {
        return service.update(id, article);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable("id") Long id) throws ArticleNotFoundException {
        service.delete(id);
    }
}
