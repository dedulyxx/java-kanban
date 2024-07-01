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


    public Task setStatus(Status status) {
        this.status = status;
        return this;
    }


    public Status getStatus() {
        return status;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if ((object.getClass() != this.getClass()) || object == null) return false;
        Task task = (Task) object;
        return id == task.id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + ((nameTask == null) ? 0 : nameTask.hashCode()) + ((description == null) ? 0 : description.hashCode()) + id;
        return hash;
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
