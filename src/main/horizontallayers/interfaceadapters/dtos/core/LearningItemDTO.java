package horizontallayers.interfaceadapters.dtos.core;

import java.time.Instant;

public interface LearningItemDTO {
    String getName();
    Instant getCreatedAt();
    Instant getLastModifiedAt();
}
