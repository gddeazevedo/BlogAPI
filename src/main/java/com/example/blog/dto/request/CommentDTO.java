package com.example.blog.dto.request;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class CommentDTO {
    private Long id;

    @NotBlank
    @NotNull
    @NotEmpty
    @Size(min = 1, max = 150)
    private String body;

    @NotNull
    private Long articleId;

    @NotNull
    private Long authorId;
}
