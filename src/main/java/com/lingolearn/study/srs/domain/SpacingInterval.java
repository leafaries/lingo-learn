package com.lingolearn.study.srs.domain;

import java.time.Duration;

public record SpacingInterval(
        int level,
        Duration interval,
        double easeFactor
) {
}
