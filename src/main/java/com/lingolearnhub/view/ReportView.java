package com.lingolearnhub.view;

import com.lingolearnhub.entity.UserProgress;

public class ReportView {
    public void displayProgress(UserProgress userProgress) {
        System.out.println("Progress: " + userProgress.getProgess() + "%");
    }
}
