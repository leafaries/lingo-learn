package com.lingolearnhub.report;

import com.lingolearnhub.model.UserProgress;

public class SimpleReportGenerator implements ReportGenerator {
    @Override
    public String generate(UserProgress progress) {
        // Generowanie prostego raportu
        return "Prosty raport z postepów";
    }
}
