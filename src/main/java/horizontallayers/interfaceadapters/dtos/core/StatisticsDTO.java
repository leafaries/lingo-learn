package horizontallayers.interfaceadapters.dtos.core;

import java.time.Duration;

public interface StatisticsDTO {
    int getTotalAnswers();
    int getCorrectAnswers();
    double getAccuracy();
    Duration getTotalTime();
}
