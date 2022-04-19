package com.example.blog.dto.request;

import java.util.List;

import javax.validation.constraints.Email;
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
public class AuthorDTO {
    private Long id;

    @NotBlank
    @NotEmpty
    @NotNull
    @Size(min = 1, max = 100)
    private String name;
    
    @Email
    @NotNull
    @NotBlank
    @NotEmpty
    private String email;

    @JsonIgnoreProperties({"comments"})
    @Builder.Default
    private List<ArticleDTO> articles = List.of();

    @JsonIgnoreProperties({"authorId"})
    @Builder.Default
    private List<CommentDTO> comments = List.of();
}
