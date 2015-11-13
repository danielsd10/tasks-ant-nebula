package com.example.task_manager;

import java.util.Date;

public class Task {

    private Integer _taskId;
    private String _taskName;
    private Date _createdAt;

    public Task() {
        this._taskId = 0;
        this._createdAt = new Date();
    }

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

    public Date getCreatedAt() {
        return this._createdAt;
    }

    public void setCreatedAt(Date createdAt) {
        this._createdAt = createdAt;
    }

}
