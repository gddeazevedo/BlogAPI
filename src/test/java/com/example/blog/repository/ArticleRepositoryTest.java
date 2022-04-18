package com.example.blog.repository;

import com.example.blog.entity.Article;
import com.example.blog.entity.Author;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ArticleRepositoryTest {

    @Autowired
    private ArticleRepository repository;

    @Test
    void saveArticleWithAuthor() {
        Author author = Author.builder()
            .email("authos@email.com")
            .name("Author Name")
            .build();

        Article article = Article.builder()
            .title("Article Title")
            .body("Article Body")
            .author(author)
            .build();

        repository.save(article);
    }
}
