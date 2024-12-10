package com.lingolearnhub.api;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Represents the configuration options for the LingoLearn library.
 * This allows developers to specify custom settings for the learnign environment such as strategies and limits.
 */
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Configuration {

    private String learningStrategy; // e.g. "flashcard", "multiple-choice"
    private int dailyChallengesLimit; // Maximum challenges to show to the user per day.

}
