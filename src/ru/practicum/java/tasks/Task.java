package ru.practicum.java.tasks;

public class Task {

    protected final String nameTask;
    protected final String description;
    protected int taskId;
    public Status status;


    public Task(String nameTask, String description) {
        this.nameTask = nameTask;
        this.description = description;
        this.status = Status.NEW;
    }


    public Task setStatus(Status Status) {
        status = Status;
        return this;
    }


    public Status getStatus() {
        return status;
    }


    @Override
    public String toString() {
        return " Задача " + taskId + " { " +
                "nameTask='" + nameTask + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
}
