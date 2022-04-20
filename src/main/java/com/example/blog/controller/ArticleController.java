package com.example.blog.controller;

import com.example.blog.exception.AuthorNotFoundException;
import com.example.blog.service.ArticleService;
import com.example.blog.dto.request.ArticleDTO;
import com.example.blog.exception.ArticleNotFoundException;
import com.example.blog.exception.ArticleNotValidException;

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

import java.util.List;

@RestController
@RequestMapping("/articles")
public class ArticleController {
    
    @Autowired
    private ArticleService service;

    @GetMapping
    public List<ArticleDTO> findAll(
        @RequestParam(
            required = false,
            name = "title"
        )
        String title,

        @RequestParam(
            required = false,
            name = "authorId"
        )
        Long authorId
    ) {
        if (title != null && authorId != null) {
            return service.findByTitleAndAuthor(title, authorId);
        }

        if (title != null) {
            return service.findBy(title);
        }

        if (authorId != null) {
            return service.findByAuthor(authorId);
        }

        return service.findAll();
    }

    @GetMapping("/{id}")
    public ArticleDTO findBy(@PathVariable("id") Long id) throws ArticleNotFoundException {
        return service.findBy(id);
    }

    @GetMapping("/author/{author_id}")
    public List<ArticleDTO> findByAuthor(@PathVariable("author_id") Long authorId) {
        return service.findByAuthor(authorId);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ArticleDTO create(
        @RequestBody ArticleDTO articleDTO
    ) throws ArticleNotValidException, AuthorNotFoundException {
        return service.create(articleDTO);
    }

    @PutMapping("/{id}")
    @ResponseStatus(HttpStatus.OK)
    public ArticleDTO update(
        @PathVariable("id") Long id,
        @RequestBody ArticleDTO articleDTO
    ) throws ArticleNotFoundException, ArticleNotValidException
    {
        return service.update(id, articleDTO);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable("id") Long id) throws ArticleNotFoundException {
        service.delete(id);
    }
}
