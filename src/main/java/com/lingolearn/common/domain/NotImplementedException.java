package com.lingolearn.common.domain;

public class NotImplementedException extends RuntimeException {
    public NotImplementedException() {
        super("This method has not been implemented yet");
    }

    public NotImplementedException(String message) {
        super(message);
    }
}
