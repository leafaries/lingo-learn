package com.lingolearn.vocabulary.category.domain;

/**
 * Exception thrown when attempting to operate on a category that doesn't exist.
 */
public class CategoryNotFoundException extends RuntimeException {

    public CategoryNotFoundException(Long id) {
        super("Category not found with ID: " + id);
    }

    public CategoryNotFoundException(String name) {
        super("Category not found with name: " + name);
    }

    public CategoryNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
