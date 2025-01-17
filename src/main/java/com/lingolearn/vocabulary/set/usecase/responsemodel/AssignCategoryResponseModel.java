package com.lingolearn.vocabulary.set.usecase.responsemodel;

import java.util.UUID;

public record AssignCategoryResponseModel(
        UUID setId,
        UUID categoryId
) {
}
