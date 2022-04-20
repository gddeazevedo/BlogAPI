package com.example.blog.service;

import java.util.List;
import java.util.stream.Collectors;

import com.example.blog.entity.Author;
import com.example.blog.exception.AuthorNotFoundException;
import com.example.blog.repository.ArticleRepository;
import com.example.blog.dto.request.ArticleDTO;
import com.example.blog.entity.Article;
import com.example.blog.exception.ArticleNotFoundException;
import com.example.blog.exception.ArticleNotValidException;
import com.example.blog.helper.ArticleHelper;
import com.example.blog.mapper.ArticleMapper;
import com.example.blog.repository.AuthorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

    ArticleMapper mapper = ArticleMapper.INSTANCE;

    @Autowired
    private ArticleRepository repository;

    @Autowired
    private ArticleHelper helper;

    @Autowired
    private AuthorRepository authorRepository;

    public List<ArticleDTO> findAll() {
        List<Article> articles = repository.findAll();
        return toDTOList(articles);
    }

    public List<ArticleDTO> findBy(String title) {
        List<Article> articles = repository.findByTitle(title);
        return toDTOList(articles);
    }

    public List<ArticleDTO> findByAuthor(Long authorId) {
        List<Article> articles = repository.findAllByAuthor(authorId);
        return toDTOList(articles);
    }

    public List<ArticleDTO> findByTitleAndAuthor(String title, Long authorId) {
        List<Article> articles = repository.findAllByTitleAndAuthor(title, authorId);
        return toDTOList(articles);
    } 

    public ArticleDTO findBy(Long id) throws ArticleNotFoundException {
        Article article = repository.findById(id).orElseThrow(() ->
            new ArticleNotFoundException(id)
        );
        return mapper.toDTO(article);
    }

    public ArticleDTO create(ArticleDTO articleDTO) throws ArticleNotValidException, AuthorNotFoundException {
        helper.raiseExceptionIfAttributesAreNotValid(articleDTO);

        Long authorId = articleDTO.getAuthorId();

        Author author = authorRepository.findById(authorId).orElseThrow(() ->
            new AuthorNotFoundException(authorId)
        );

        Article article = mapper.toModel(articleDTO);

        article.setAuthor(author);

        return mapper.toDTO(repository.save(article));
    }

    public ArticleDTO update(Long id, ArticleDTO articleDTO) throws ArticleNotValidException, ArticleNotFoundException {
        Article articleToUpdate = repository.findById(id).orElseThrow(() -> 
            new ArticleNotFoundException(id)
        );

        helper.raiseExceptionIfAttributesAreNotValid(articleDTO);

        articleToUpdate.setTitle(articleDTO.getTitle());
        articleToUpdate.setBody(articleDTO.getBody());

        return mapper.toDTO(repository.save(articleToUpdate));
    }

    public void delete(Long id) throws ArticleNotFoundException {
        Article article = repository.findById(id).orElseThrow(() ->
            new ArticleNotFoundException(id)
        );

        repository.delete(article);
    }

    private List<ArticleDTO> toDTOList(List<Article> articles) {
        return articles.stream()
            .map(mapper::toDTO)
            .collect(Collectors.toList());
    }
}
