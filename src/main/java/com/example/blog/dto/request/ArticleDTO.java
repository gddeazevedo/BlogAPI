package com.example.blog.dto.request;

import java.util.List;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ArticleDTO {
    private Long id;

    @NotNull
    @NotBlank
    @NotEmpty
    @Size(min = 1, max = 50)
    private String title;

    @NotBlank
    @NotNull
    @NotEmpty
    @Size(min = 5, max = 300)
    private String body;

    @NotNull
    private Long authorId;

    @JsonIgnoreProperties({"articleId"})
    @Builder.Default
    private List<CommentDTO> comments = List.of();
}
