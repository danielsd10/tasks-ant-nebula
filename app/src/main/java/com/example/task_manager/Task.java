package com.example.task_manager;

public class Task {

    private Integer _taskId;
    private String _taskName;

    public Task(Integer id) {
        this._taskId = id;
    }

    public Integer getId() {
        return this._taskId;
    }

    public String getName() {
        return this._taskName;
    }

    public void setName(String name) {
        this._taskName = name;
    }

}
