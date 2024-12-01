package com.lingolearnhub.controller;

import com.lingolearnhub.view.ReportView;
import com.lingolearnhub.entity.UserProgress;

public class ReportController {
    private final ReportView view;
    private final UserProgress progress;

    public ReportController(ReportView view, UserProgress progress) {
        this.view = view;
        this.progress = progress;
    }

    public void showReport() {
        view.displayProgress(progress);
    }
}