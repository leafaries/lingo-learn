package horizontallayers.interfaceadapters.dtos.study;

import horizontallayers.interfaceadapters.dtos.WordDTO;
import horizontallayers.interfaceadapters.dtos.core.StatisticsDTO;

import java.time.Duration;

/*
 - Represent transient state
 - Referenced through their parent sessions if needed
 */
public record StudyProgressDTO(
        WordDTO currentWord,
        boolean isCorrect,
        int totalAnswered,
        int correctAnswers,
        double accuracy,
        Duration averageResponseTime
) implements StatisticsDTO {
    @Override
    public int getTotalAnswers() {
        return totalAnswered;
    }

    @Override
    public int getCorrectAnswers() {
        return correctAnswers;
    }

    @Override
    public double getAccuracy() {
        return accuracy;
    }

    @Override
    public Duration getTotalTime() {
        return averageResponseTime.multipliedBy(totalAnswered);
    }
}
