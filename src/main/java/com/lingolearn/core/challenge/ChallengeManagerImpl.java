package com.lingolearn.core.challenge;

import com.lingolearn.core.LingoLearn;

public class ChallengeManagerImpl implements LingoLearn.ChallengeManager {
    private final ReviewImpl review;
    private final TestImpl test;
    private final DailyChallengeImpl dailyChallenge;

    public ChallengeManagerImpl() {
        this.review = new ReviewImpl();
        this.test = new TestImpl();
        this.dailyChallenge = new DailyChallengeImpl();
    }

    @Override
    public DailyChallenge daily() {
        return dailyChallenge;
    }

    @Override
    public Review review() {
        return review;
    }

    @Override
    public Test test() {
        return test;
    }
}
