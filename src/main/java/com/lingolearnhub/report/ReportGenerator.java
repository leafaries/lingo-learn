package com.lingolearnhub.report;

import com.lingolearnhub.progress.UserProgress;

public interface ReportGenerator {
    String generate(UserProgress progress);
}
