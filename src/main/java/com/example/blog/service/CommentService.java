package com.example.blog.service;

import java.util.List;
import java.util.stream.Collectors;

import com.example.blog.entity.Comment;
import com.example.blog.exception.ArticleNotFoundException;
import com.example.blog.exception.AuthorNotFoundException;
import com.example.blog.exception.CommentNotFoundException;
import com.example.blog.exception.CommentNotValidException;
import com.example.blog.helper.CommentHelper;
import com.example.blog.mapper.CommentMapper;
import com.example.blog.dto.request.CommentDTO;
import com.example.blog.entity.Article;
import com.example.blog.entity.Author;
import com.example.blog.repository.CommentRepository;
import com.example.blog.repository.ArticleRepository;
import com.example.blog.repository.AuthorRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommentService {

    private CommentMapper mapper = CommentMapper.INSTANCE;
    
    @Autowired
    private CommentRepository repository;

    @Autowired
    private CommentHelper helper;

    @Autowired
    private ArticleRepository articleRepository;

    @Autowired
    private AuthorRepository authorRepository;

    public List<CommentDTO> findAll() {
        List<Comment> comments = repository.findAll();
        return toDTOList(comments);
    }

    public List<CommentDTO> findAllByAuthor(Long authorId) {
        List<Comment> comments = repository.findAllByAuthor(authorId);
        return toDTOList(comments);
    }

    public List<CommentDTO> findAllByArticle(Long articleId) {
        List<Comment> comments = repository.findAllByArticle(articleId);
        return toDTOList(comments);
    }

    public List<CommentDTO> findAllByArticleAndAuthor(Long articleId, Long authorId) {
        List<Comment> comments = repository.findAllByArticleAndAuthor(articleId, authorId);
        return toDTOList(comments);
    }

    public CommentDTO findBy(Long id) throws CommentNotFoundException {
        Comment comment = repository.findById(id).orElseThrow(() ->
            new CommentNotFoundException(id)
        );

        return mapper.toDTO(comment);
    }

    public CommentDTO create(CommentDTO commentDTO) throws ArticleNotFoundException, AuthorNotFoundException, CommentNotValidException {
        Long articleId = commentDTO.getArticleId();
        Long authorId = commentDTO.getAuthorId();
        
        Article article = articleRepository.findById(articleId).orElseThrow(() ->
            new ArticleNotFoundException(articleId)
        );

        Author author = authorRepository.findById(authorId).orElseThrow(() ->
            new AuthorNotFoundException(authorId)
        );

        helper.raiseExceptionIfAttributesAreNotValid(commentDTO);

        Comment comment = mapper.toModel(commentDTO);

        comment.setAuthor(author);
        comment.setArticle(article);

        return mapper.toDTO(repository.save(comment));
    }

    public CommentDTO update(Long id, CommentDTO commentDTO) throws CommentNotFoundException, CommentNotValidException {
        Comment commentToUpdate = repository.findById(id).orElseThrow(() ->
            new CommentNotFoundException(id)
        );

        helper.raiseExceptionIfAttributesAreNotValid(commentDTO);

        commentToUpdate.setBody(commentDTO.getBody());

        return mapper.toDTO(repository.save(commentToUpdate));
    }

    public void delete(Long id) throws CommentNotFoundException {
        Comment comment = repository.findById(id).orElseThrow(() ->
            new CommentNotFoundException(id)
        );

        repository.delete(comment);
    }

    private List<CommentDTO> toDTOList(List<Comment> comments) {
        return comments.stream()
            .map(mapper::toDTO)
            .collect(Collectors.toList());
    }
}
