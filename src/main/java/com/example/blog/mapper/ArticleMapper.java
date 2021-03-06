package com.example.blog.mapper;

import com.example.blog.dto.request.ArticleDTO;
import com.example.blog.entity.Article;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface ArticleMapper {
    ArticleMapper INSTANCE = Mappers.getMapper(ArticleMapper.class);

    Article toModel(ArticleDTO articleDTO);

    @Mapping(source = "article.author.id", target = "authorId")
    ArticleDTO toDTO(Article article);
}
