package com.example.blog.repository;

import java.util.Optional;

import com.example.blog.entity.Author;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {
    Optional<Author> findByEmail(String email);
}
