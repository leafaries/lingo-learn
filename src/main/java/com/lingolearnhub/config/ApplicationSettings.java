package com.lingolearnhub.config;

public class ApplicationSettings {

    private static ApplicationSettings instance;

    private ApplicationSettings() {}

    public static ApplicationSettings getInstance() {
        if (instance == null) {
            instance = new ApplicationSettings();
        }
        return instance;
    }

}
