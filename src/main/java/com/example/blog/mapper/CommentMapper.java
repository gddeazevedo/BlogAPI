package com.example.blog.mapper;

import com.example.blog.dto.request.CommentDTO;
import com.example.blog.entity.Comment;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

@Mapper
public interface CommentMapper {
    CommentMapper INSTANCE = Mappers.getMapper(CommentMapper.class);

    Comment toModel(CommentDTO commentDTO);

    @Mapping(source = "comment.author.id", target = "authorId")
    @Mapping(source = "comment.article.id", target = "articleId")
    CommentDTO toDTO(Comment comment);
}
