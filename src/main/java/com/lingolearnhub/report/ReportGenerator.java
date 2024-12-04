package com.lingolearnhub.report;

import com.lingolearnhub.model.UserProgress;

public interface ReportGenerator {
    String generate(UserProgress progress);
}
