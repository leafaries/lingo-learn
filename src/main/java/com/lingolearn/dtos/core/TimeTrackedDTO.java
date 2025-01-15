package com.lingolearn.dtos.core;

import java.time.Duration;
import java.time.Instant;

public interface TimeTrackedDTO {
    Instant getStartTime();
    Instant getEndTime();

    default Duration getDuration() {
        return Duration.between(getStartTime(), getEndTime());
    }
}
