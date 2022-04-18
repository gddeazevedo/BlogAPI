package com.example.blog.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Article")
@Table(name = "articles")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Article {

    @Id
    @SequenceGenerator(
        name = "articles_sequence",
        sequenceName = "articles_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "articles_sequence"
    )
    private Long id;

    @NotBlank
    @NotNull
    @Size(min = 1, max = 50)
    @Column(name = "title", nullable = false)
    private String title;

    @NotBlank
    @NotNull
    @Size(min = 5, max = 300)
    @Column(name = "body", nullable = false)
    private String body;

    @ManyToOne(
        cascade = CascadeType.ALL,
        optional = false
    )
    @JoinColumn(
        name = "author_id",
        referencedColumnName = "id", // Author's id
        nullable = false,
        updatable = false
    )
    @JsonBackReference
    private Author author;

    public Article(String title, String body) {
        this.title = title;
        this.body = body;
    }
}
