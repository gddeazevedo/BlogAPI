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

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity(name = "Comment")
@Table(name = "comments")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Comment {
    
    @Id
    @SequenceGenerator(
        name = "comments_sequence",
        sequenceName = "comments_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = GenerationType.SEQUENCE,
        generator = "comments_sequence"
    )
    private Long id;

    @NotNull
    @NotBlank
    @Size(min = 1, max = 150)
    @Column(name = "body", nullable = false)
    private String body;

    @ManyToOne(
        cascade = CascadeType.ALL
    )
    @JoinColumn(
        name = "article_id",
        referencedColumnName = "id", // Article's id
        nullable = false,
        updatable = false
    )
    @JsonIgnoreProperties("comments")
    private Article article;

    @ManyToOne(
        cascade = CascadeType.ALL
    )
    @JoinColumn(
        name = "author_id",
        referencedColumnName = "id", // Author's id
        nullable = false,
        updatable = false
    )
    @JsonIgnoreProperties(value = {"articles", "comments"})
    private Author author;

    public Comment(String body, Article article, Author author) {
        this.body = body;
        this.article = article;
        this.author = author;
    }

}