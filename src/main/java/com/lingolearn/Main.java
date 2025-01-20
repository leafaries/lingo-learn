package com.lingolearn;

public class Main {
    public static void main(String[] args) {
        var app = new LingoLearnApplication();

        Runtime.getRuntime().addShutdownHook(new Thread(app::shutdown));

        try {
            app.start();
        } catch (Exception e) {
            System.err.println("Application failed: " + e.getMessage());
            System.exit(1);
        }
    }
}
