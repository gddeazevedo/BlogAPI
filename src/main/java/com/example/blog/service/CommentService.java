package com.example.blog.service;

import java.util.List;

import com.example.blog.entity.Comment;
import com.example.blog.exception.ArticleNotFoundException;
import com.example.blog.exception.AuthorNotFoundException;
import com.example.blog.exception.CommentNotFoundException;
import com.example.blog.exception.CommentNotValidException;
import com.example.blog.helper.CommentHelper;
import com.example.blog.entity.Article;
import com.example.blog.entity.Author;
import com.example.blog.repository.CommentRepository;
import com.example.blog.repository.ArticleRepository;
import com.example.blog.repository.AuthorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {
    
    @Autowired
    private CommentRepository repository;

    @Autowired
    private CommentHelper helper;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public List<Comment> findAll() {
        return repository.findAll();
    }

    public List<Comment> findAllByAuthor(Long authorId) {
        return repository.findAllByAuthor(authorId);
    }

    public List<Comment> findAllByArticle(Long articleId) {
        return repository.findAllByArticle(articleId);
    }

    public List<Comment> findAllByArticleAndAuthor(Long articleId, Long authorId) {
        return repository.findAllByArticleAndAuthor(articleId, authorId);
    }

    public Comment findBy(Long id) throws CommentNotFoundException {
        return repository.findById(id).orElseThrow(() ->
            new CommentNotFoundException(id)
        );
    }

    public Comment create(Long articleId, Long authorId, Comment comment) throws ArticleNotFoundException, AuthorNotFoundException, CommentNotValidException {
        Article article = articleRepository.findById(authorId).orElseThrow(() ->
            new ArticleNotFoundException(articleId)
        );

        Author author = authorRepository.findById(authorId).orElseThrow(() ->
            new AuthorNotFoundException(authorId)
        );

        helper.raiseExceptionIfAttributesAreNotValid(comment);

        comment.setAuthor(author);
        comment.setArticle(article);

        return repository.save(comment);
    }

    public Comment update(Long id, Comment comment) throws CommentNotFoundException, CommentNotValidException {
        Comment commentToUpdate = repository.findById(id).orElseThrow(() ->
            new CommentNotFoundException(id)
        );

        helper.raiseExceptionIfAttributesAreNotValid(comment);

        commentToUpdate.setBody(comment.getBody());

        return repository.save(commentToUpdate);
    }

    public void delete(Long id) throws CommentNotFoundException {
        Comment comment = repository.findById(id).orElseThrow(() ->
            new CommentNotFoundException(id)
        );

        repository.delete(comment);
    }
}
