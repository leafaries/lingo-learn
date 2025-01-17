package horizontallayers.infrastructure.jpaentities;

import jakarta.persistence.*;

import java.time.Duration;
import java.time.LocalDateTime;

@Entity
@Table(name = "answers")
public class AnswerEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "session_id", nullable = false)
    private SessionEntity session;

    @ManyToOne
    @JoinColumn(name = "word_id", nullable = false)
    private WordEntity word;

    @Column(nullable = false)
    private String userInput;

    @Column(nullable = false)
    private boolean correct;

    @Column(nullable = false)
    private Duration responseTime;

    @Column(nullable = false)
    private LocalDateTime timestamp = LocalDateTime.now();

    // Additional fields for analysis
    @Column
    private Integer characterAccuracy; // Percentage of correct characters

    @Column
    private Boolean hintUsed = false;

    @Column(length = 1024)
    private String feedback;

    // Constructors
    public AnswerEntity() {
    }

    public AnswerEntity(SessionEntity session, WordEntity word, String userInput, boolean correct, Duration responseTime) {
        this.session = session;
        this.word = word;
        this.userInput = userInput;
        this.correct = correct;
        this.responseTime = responseTime;
        calculateCharacterAccuracy();
    }

    // Getters and Setters
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public SessionEntity getSession() {
        return session;
    }

    public void setSession(SessionEntity session) {
        this.session = session;
    }

    public WordEntity getWord() {
        return word;
    }

    public void setWord(WordEntity word) {
        this.word = word;
    }

    public String getUserInput() {
        return userInput;
    }

    public void setUserInput(String userInput) {
        this.userInput = userInput;
        calculateCharacterAccuracy();
    }

    public boolean isCorrect() {
        return correct;
    }

    public void setCorrect(boolean correct) {
        this.correct = correct;
    }

    public Duration getResponseTime() {
        return responseTime;
    }

    public void setResponseTime(Duration responseTime) {
        this.responseTime = responseTime;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    public Integer getCharacterAccuracy() {
        return characterAccuracy;
    }

    public void setCharacterAccuracy(Integer characterAccuracy) {
        this.characterAccuracy = characterAccuracy;
    }

    public Boolean getHintUsed() {
        return hintUsed;
    }

    public void setHintUsed(Boolean hintUsed) {
        this.hintUsed = hintUsed;
    }

    public String getFeedback() {
        return feedback;
    }

    public void setFeedback(String feedback) {
        this.feedback = feedback;
    }

    // Utility methods
    private void calculateCharacterAccuracy() {
        if (word == null || userInput == null) {
            this.characterAccuracy = 0;
            return;
        }

        String correctAnswer = word.getTranslation().toLowerCase().trim();
        String userAnswer = userInput.toLowerCase().trim();

        // Using Levenshtein distance to calculate character accuracy
        int distance = calculateLevenshteinDistance(correctAnswer, userAnswer);
        int maxLength = Math.max(correctAnswer.length(), userAnswer.length());

        this.characterAccuracy = maxLength == 0 ? 100 :
                (int) (((double) (maxLength - distance) / maxLength) * 100);
    }

    private int calculateLevenshteinDistance(String s1, String s2) {
        int[][] dp = new int[s1.length() + 1][s2.length() + 1];

        for (int i = 0; i <= s1.length(); i++) {
            dp[i][0] = i;
        }
        for (int j = 0; j <= s2.length(); j++) {
            dp[0][j] = j;
        }

        for (int i = 1; i <= s1.length(); i++) {
            for (int j = 1; j <= s2.length(); j++) {
                if (s1.charAt(i - 1) == s2.charAt(j - 1)) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = 1 + Math.min(dp[i - 1][j - 1], // replace
                            Math.min(dp[i - 1][j],     // delete
                                    dp[i][j - 1]));     // insert
                }
            }
        }

        return dp[s1.length()][s2.length()];
    }

    public boolean wasQuick() {
        return responseTime.getSeconds() < 3;
    }

    public void addFeedback(String newFeedback) {
        if (this.feedback == null || this.feedback.isEmpty()) {
            this.feedback = newFeedback;
        } else {
            this.feedback += "; " + newFeedback;
        }
    }

    // Object overrides
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AnswerEntity)) return false;
        AnswerEntity that = (AnswerEntity) o;
        return id != null && id.equals(that.getId());
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

    @Override
    public String toString() {
        return "AnswerEntity{" +
                "id=" + id +
                ", word=" + (word != null ? word.getOriginal() : "null") +
                ", userInput='" + userInput + '\'' +
                ", correct=" + correct +
                ", responseTime=" + responseTime.toSeconds() + "s" +
                ", characterAccuracy=" + characterAccuracy + "%" +
                '}';
    }
}
