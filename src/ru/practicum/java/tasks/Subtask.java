package ru.practicum.java.tasks;

import java.util.Objects;

public class Subtask extends Task {

    private int epicId;

    public Subtask(String nameTask, String description, int epicId) {
        super(nameTask, description);
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }

    @Override
    public Subtask setStatus(Status Status) {
        super.setStatus(Status);
        return this;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if ((object.getClass() != this.getClass()) || object == null) return false;
        Subtask subtask = (Subtask) object;
        return id == subtask.id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + ((nameTask == null) ? 0 : nameTask.hashCode()) + ((description == null) ? 0 : description.hashCode()) + id + epicId;
        return hash;
    }

    @Override
    public String toString() {
        return " nameTask='" + nameTask + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public void setTaskId(int id) {
        super.setTaskId(id);
    }
}
