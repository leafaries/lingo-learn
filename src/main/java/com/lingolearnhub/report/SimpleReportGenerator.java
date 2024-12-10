package com.lingolearnhub.report;

import com.lingolearnhub.progress.UserProgress;

public class SimpleReportGenerator implements ReportGenerator {

    @Override
    public String generate(UserProgress progress) {
        // Generowanie prostego raportu
        return "Prosty raport z postepów";
    }

}
