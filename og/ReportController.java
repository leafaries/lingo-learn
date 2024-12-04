package com.og;

import com.lingolearnhub.og.ReportView;
import com.lingolearnhub.og.UserProgress;

public class ReportController {
    private final ReportView view;
    private final UserProgress progress;

    public ReportController(ReportView view, com.lingolearnhub.og.entity.UserProgress progress) {
        this.view = view;
        this.progress = progress;
    }

    public void showReport() {
        view.displayProgress(progress);
    }
}