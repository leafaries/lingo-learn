package horizontallayers.interfaceadapters.dtos.challenge;

import horizontallayers.domain.enums.SessionType;
import horizontallayers.interfaceadapters.dtos.SessionDTO;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record DailyChallengeDTO(
        SessionDTO session,
        LocalDate date,
        boolean completed,
        int targetWordCount,
        double requiredAccuracy,
        LocalDateTime startTime,
        LocalDateTime completionTime,
        SessionType challengeType
) {

}
