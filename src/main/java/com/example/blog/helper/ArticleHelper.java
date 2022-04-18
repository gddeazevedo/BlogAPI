package com.example.blog.helper;

import com.example.blog.entity.Article;
import com.example.blog.exception.ArticleNotValidException;

import org.springframework.stereotype.Component;

@Component
public class ArticleHelper implements Helper<Article> {

    @Override
    public void raiseExceptionIfAttributesAreNotValid(Article article) throws ArticleNotValidException {
        if (isTitleEmptyOrNull(article) && isBodyEmptyOrNull(article)) {
            throw new ArticleNotValidException("Title and body invalid! Please provide a correct title and a correct body");
        }

        if (isTitleEmptyOrNull(article)) {
            throw new ArticleNotValidException("Title is empty or null. Please add a title!");
        }

        if (isBodyEmptyOrNull(article)) {
            throw new ArticleNotValidException("Body is empty or null. Please add a body!");
        }
    }

    private boolean isTitleEmptyOrNull(Article article) {
        // (min = 1, max = 50)

        return "".equals(article.getTitle()) || article.getTitle() == null;
    }

    private boolean isBodyEmptyOrNull(Article article) {
        return "".equals(article.getBody()) || article.getBody() == null;
    }
}
