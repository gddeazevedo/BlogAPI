package com.example.blog.repository;

import java.util.List;

import com.example.blog.entity.Article;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Long> {
    List<Article> findByTitle(String title);

    @Query(
        value = "SELECT * FROM articles WHERE author_id = :author_id",
        nativeQuery = true
    )
    List<Article> findAllByAuthor(@Param("author_id") Long authorId);
}
