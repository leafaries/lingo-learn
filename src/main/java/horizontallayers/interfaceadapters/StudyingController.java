package horizontallayers.interfaceadapters;

public class StudyingController {
    private final StudyManagementInputPort studyManagement;
    private final StudyPresenter presenter;

    public StudySessionViewModel initializeStudySession(VocabularySet vocabularySet, StudyMode mode) {
        StartSessionResponse response = studyManagement.startSession(
                new StartSessionRequest(vocabularySet.getId(), mode)
        );
        return presenter.prepareViewModel(response);
    }

    public AnswerResultViewModel handleUserAnswer(String sessionId, String userInput) {
        SubmitAnswerResponse response = studyManagement.submitAnswer(
                new SubmitAnswerRequest(sessionId, userInput)
        );
        return presenter.prepareAnswerViewModel(response);
    }

    public StudyProgressViewModel getProgress(String sessionId) {
        StudyProgressResponse response = studyManagement.getProgress(
                new GetProgressRequest(sessionId)
        );
        return presenter.prepareProgressViewModel(response);
    }

    public SessionSummaryViewModel finishSession(String sessionId) {
        CompleteSessionResponse response = studyManagement.completeSession(
                new CompleteSessionRequest(sessionId)
        );
        return presenter.prepareSummaryViewModel(response);
    }

    public ReviewSessionViewModel startReview() {
        ReviewSessionResponse response = studyManagement.startReview();
        return presenter.prepareReviewViewModel(response);
    }

    public DailyChallengeViewModel startDailyChallenge() {
        DailyChallengeResponse response = studyManagement.startDailyChallenge();
        return presenter.prepareChallengeViewModel(response);
    }
}
