package com.lingolearn.vocabulary.set.usecase.requestmodel;

import java.util.UUID;

public record AssignCategoryRequestModel(
        UUID setId,
        UUID categoryId
) {
}
