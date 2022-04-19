package com.example.blog.helper;

import com.example.blog.dto.request.ArticleDTO;
import com.example.blog.entity.Article;
import com.example.blog.exception.ArticleNotValidException;

import org.springframework.stereotype.Component;

@Component
public class ArticleHelper implements Helper<ArticleDTO> {

    @Override
    public void raiseExceptionIfAttributesAreNotValid(ArticleDTO articleDTO) throws ArticleNotValidException {
        if (isTitleEmptyOrNull(articleDTO.getTitle()) && isBodyEmptyOrNull(articleDTO.getBody())) {
            throw new ArticleNotValidException("Title and body invalid! Please provide a correct title and a correct body");
        }

        if (isTitleEmptyOrNull(articleDTO.getTitle())) {
            throw new ArticleNotValidException("Title is empty or null. Please add a title!");
        }

        if (isBodyEmptyOrNull(articleDTO.getBody())) {
            throw new ArticleNotValidException("Body is empty or null. Please add a body!");
        }
    }

    private boolean isTitleEmptyOrNull(String title) {
        // (min = 1, max = 50)

        return "".equals(title) || title == null;
    }

    private boolean isBodyEmptyOrNull(String body) {
        return "".equals(body) || body == null;
    }
}
