package com.example.blog.helper;

import org.springframework.stereotype.Component;

@Component
public interface Helper<T> {
    void raiseExceptionIfAttributesAreNotValid(T entity) throws Exception;
}
