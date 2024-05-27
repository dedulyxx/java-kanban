package ru.practicum.java.tasks;

public class Task {

    protected final String nameTask;
    protected final String description;
    protected int id;
    protected Status status;


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
        return " Задача " + id + " { " +
                "nameTask='" + nameTask + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status;
    }

    public int getId() {
        return id;
    }

    public void setTaskId(int id) {
        this.id = id;
    }
}
