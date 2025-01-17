package horizontallayers.usecases.exception;

public class StudySessionException extends RuntimeException {
    public StudySessionException(String message) {
        super(message);
    }

    public StudySessionException(String message, Throwable cause) {
        super(message, cause);
    }
}
