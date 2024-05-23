package ru.practicum.java.tasks;

public class Epic extends Task {

    public final int epicId;

    public Epic(String nameTask, String description) {
        super(nameTask, description);
        this.epicId = TaskManager.generateEpicId();
    }

    @Override
    public void setStatus(Enum newStatus) {
        super.setStatus(newStatus);
    }

    @Override
    public String toString() {
        return " Эпик { " +
                "nameTask='" + nameTask + '\'' +
                ", description=" + description +
                ", status=" + status +
                '}';
    }
}
