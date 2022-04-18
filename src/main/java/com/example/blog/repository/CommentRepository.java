package com.example.blog.repository;

import java.util.List;

import com.example.blog.entity.Comment;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {

    @Query(
        value = "SELECT * FROM comments WHERE article_id = :article_id",
        nativeQuery = true
    )
    List<Comment> findAllByArticle(@Param("article_id") Long articleId);

    @Query(
        value = "SELECT * FROM comments WHERE author_id = :author_id",
        nativeQuery = true
    )
    List<Comment> findAllByAuthor(@Param("author_id") Long authorId);

    @Query(
        value = "SELECT * FROM comments WHERE article_id = :article_id AND author_id = :author_id",
        nativeQuery = true
    )
    List<Comment> findAllByArticleAndAuthor(
        @Param("article_id") Long articleId,
        @Param("author_id") Long authorId
    );
}
