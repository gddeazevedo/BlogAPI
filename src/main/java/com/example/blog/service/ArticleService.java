package com.example.blog.service;

import java.util.List;

import com.example.blog.entity.Author;
import com.example.blog.exception.AuthorNotFoundException;
import com.example.blog.repository.ArticleRepository;
import com.example.blog.entity.Article;
import com.example.blog.exception.ArticleNotFoundException;
import com.example.blog.exception.ArticleNotValidException;
import com.example.blog.helper.ArticleHelper;

import com.example.blog.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    @Autowired
    private ArticleRepository repository;

    @Autowired
    private ArticleHelper helper;

    @Autowired
    private AuthorRepository authorRepository;

    public List<Article> findAll() {
        return repository.findAll();
    }

    public List<Article> findBy(String title) {
        return repository.findByTitle(title);
    }

    public Article findBy(Long id) throws ArticleNotFoundException {
        return repository.findById(id).orElseThrow(() ->
            new ArticleNotFoundException(id)
        );
    }

    public List<Article> findByAuthor(Long authorId) {
        return repository.findAllByAuthor(authorId);
    }

    public Article create(Long authorId, Article article) throws ArticleNotValidException, AuthorNotFoundException {
        helper.raiseExceptionIfAttributesAreNotValid(article);

        Author author = authorRepository.findById(authorId).orElseThrow(() ->
            new AuthorNotFoundException(authorId)
        );

        article.setAuthor(author);

        return repository.save(article);
    }

    public Article update(Long id, Article article) throws ArticleNotValidException, ArticleNotFoundException {
        Article articleToUpdate = repository.findById(id).orElseThrow(() -> 
            new ArticleNotFoundException(id)
        );

        helper.raiseExceptionIfAttributesAreNotValid(article);

        articleToUpdate.setTitle(article.getTitle());
        articleToUpdate.setBody(article.getBody());

        return repository.save(articleToUpdate);
    }

    public void delete(Long id) throws ArticleNotFoundException {
        Article article = repository.findById(id).orElseThrow(() ->
            new ArticleNotFoundException(id)
        );

        repository.delete(article);
    }
}
