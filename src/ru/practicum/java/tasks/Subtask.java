package ru.practicum.java.tasks;

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
    public String toString() {
        return " nameTask='" + nameTask + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }

    @Override
    public void setTaskId(int id) {
        this.id = id;
    }
}
