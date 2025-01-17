package com.lingolearn.persistence.settings.domain;

public record UserSettings(
        StudyPreferences studyPreferences,
        NotificationSettings notifications,
        DisplaySettings display
) {

}
