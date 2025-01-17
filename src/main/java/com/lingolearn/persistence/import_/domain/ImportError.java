package com.lingolearn.persistence.import_.domain;

import java.time.LocalDateTime;

public record ImportError(
        String code,
        String message,
        LocalDateTime timestamp
) {
}
