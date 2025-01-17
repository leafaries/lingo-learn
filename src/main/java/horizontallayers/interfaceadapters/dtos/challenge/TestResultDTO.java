package horizontallayers.interfaceadapters.dtos.challenge;

import horizontallayers.interfaceadapters.dtos.VocabularySetDTO;
import horizontallayers.interfaceadapters.dtos.WordDTO;
import horizontallayers.interfaceadapters.dtos.core.StatisticsDTO;
import horizontallayers.interfaceadapters.dtos.core.TimeTrackedDTO;
import horizontallayers.interfaceadapters.dtos.study.AnswerDTO;

import java.time.Duration;
import java.time.Instant;
import java.util.List;

public record TestResultDTO(
        VocabularySetDTO vocabularySet,
        double score,
        List<AnswerDTO> answers,
        List<WordDTO> incorrectAnswers,
        Duration testDuration,
        Instant startTime,
        Instant endTime
) implements TimeTrackedDTO, StatisticsDTO {

    @Override
    public int getTotalAnswers() {
        return answers.size();
    }

    @Override
    public int getCorrectAnswers() {
        return answers.size() - incorrectAnswers.size();
    }

    @Override
    public double getAccuracy() {
        return score;
    }

    @Override
    public Duration getTotalTime() {
        return testDuration;
    }

    @Override
    public Instant getStartTime() {
        return startTime;
    }

    @Override
    public Instant getEndTime() {
        return endTime;
    }
}
