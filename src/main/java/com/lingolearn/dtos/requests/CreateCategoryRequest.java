package com.lingolearn.dtos.requests;

public record CreateCategoryRequest(
        String name,
        String description
) {
}
