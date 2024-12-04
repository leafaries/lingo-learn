package com.og;

import com.og.UserProgress;

public class ReportView {
    public void displayProgress(UserProgress userProgress) {
        System.out.println("Progress: " + userProgress.getProgess() + "%");
    }
}
