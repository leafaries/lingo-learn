package com.lingolearn.dtos.requests;

public record CreateWordRequest(
        String original,
        String translation
) {
}
