package com.vitor.taskly.model;

public class Task {

    private String taskName;
    private String description;

    public Task(String taskName, String description) {
        this.taskName = taskName;
        this.description = description;
    }

    public String getTaskName() {
        return taskName;
    }

    public String getDescription() {
        return description;
    }
}
