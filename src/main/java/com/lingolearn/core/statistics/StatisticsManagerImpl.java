package com.lingolearn.core.statistics;

import com.lingolearn.core.LingoLearn;
import com.lingolearn.dtos.WordDTO;
import com.lingolearn.dtos.statistics.ReportConfigDTO;
import com.lingolearn.dtos.statistics.ReportDTO;
import com.lingolearn.dtos.statistics.StudyStatisticsDTO;
import com.lingolearn.entities.StatisticsEntity;
import com.lingolearn.repos.SessionRepository;
import com.lingolearn.repos.StatisticsRepository;
import com.lingolearn.repos.WordRepository;

import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class StatisticsManagerImpl implements LingoLearn.StatisticsManager {
    private final StatisticsRepository statisticsRepo;
    private final SessionRepository sessionRepo;
    private final WordRepository wordRepo;

    public StatisticsManagerImpl() {
        this.statisticsRepo = new StatisticsRepository();
        this.sessionRepo = new SessionRepository();
        this.wordRepo = new WordRepository();
    }

    @Override
    public StudyStatisticsDTO getStudyStats() {
        StatisticsEntity stats = getOrCreateTodayStats();

        return new StudyStatisticsDTO(
                stats.getTotalWordsLearned(),
                stats.getCorrectAnswers(),
                stats.getTotalAnswers(),
                calculateAccuracy(stats),
                stats.getTotalStudyTime(),
                stats.getDailyChallengesCompleted(),
                stats.getDate());
    }

    @Override
    public void resetStats() {
        StatisticsEntity stats = getOrCreateTodayStats();
        stats.setTotalWordsLearned(0);
        stats.setCorrectAnswers(0);
        stats.setTotalAnswers(0);
        stats.setTotalStudyTime(Duration.ZERO);
        stats.setDailyChallengesCompleted(0);
        stats.setStreakDays(0);
        statisticsRepo.save(stats);
    }

    @Override
    public ReportDTO generateReport(ReportConfigDTO config) {
        Map<LocalDate, StudyStatisticsDTO> dailyStats = statisticsRepo
                .findBetweenDates(config.fromDate(), config.toDate())
                .stream()
                .collect(Collectors.toMap(
                        StatisticsEntity::getDate,
                        this::mapToStudyStatisticsDTO
                ));

        List<WordDTO> problematicWords = config.includeProblematicWords() ?
                wordRepo.findProblematicWords(0.7).stream()
                        .map(this::mapToWordDTO)
                        .collect(Collectors.toList()) :
                List.of();

        Duration totalTime = dailyStats.values().stream()
                .map(StudyStatisticsDTO::studyTime)
                .reduce(Duration.ZERO, Duration::plus);

        return new ReportDTO(
                Instant.now(),
                config,
                dailyStats,
                problematicWords,
                getMostStudiedSets(config.fromDate(), config.toDate()),
                calculateTotalWordsLearned(dailyStats.values()),
                calculateOverallAccuracy(dailyStats.values()),
                totalTime
        );
    }

    private StatisticsEntity getOrCreateTodayStats() {
        return statisticsRepo.findByDate(LocalDate.now())
                .orElseGet(() -> {
                    StatisticsEntity stats = new StatisticsEntity();
                    return statisticsRepo.save(stats);
                });
    }

    private double calculateAccuracy() {
        StatisticsEntity stats = getOrCreateTodayStats();
        if (stats.getTotalAnswers() == 0) return 0.0;
        return (double) stats.getCorrectAnswers() / stats.getTotalAnswers();
    }

    private List<WordDTO> calculateMostProblematicWords() {
        return wordRepo.findProblematicWords(0.7).stream()
                .map(this::mapToWordDTO)
                .collect(Collectors.toList());
    }
}
