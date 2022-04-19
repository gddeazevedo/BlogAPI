package com.example.blog.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity(name = "Comment")
@Table(name = "comments")
@Getter
@Setter
@ToString
@AllArgsConstructor
@NoArgsConstructor
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

    @Column(name = "body", nullable = false)
    private String body;

    @ManyToOne
    @JoinColumn(
        name = "article_id",
        referencedColumnName = "id", // Article's id
        nullable = false,
        updatable = false
    )
    @ToString.Exclude
    private Article article;

    @ManyToOne
    @JoinColumn(
        name = "author_id",
        referencedColumnName = "id", // Author's id
        nullable = false,
        updatable = false
    )
    private Author author;

    public Comment(String body, Article article, Author author) {
        this.body = body;
        this.article = article;
        this.author = author;
    }

}