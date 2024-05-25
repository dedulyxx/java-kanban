package ru.practicum.java.tasks;

public class Epic extends Task {

    private int epicId;
    private int taskId;

    public Epic(String nameTask, String description) {
        super(nameTask, description);
    }

    @Override
    public Epic setStatus(Status newStatus) {
        super.setStatus(newStatus);
        return this;
    }

    @Override
    public String toString() {
        return " Эпик { " +
                "nameTask='" + nameTask + '\'' +
                ", description=" + description +
                ", status=" + status +
                '}';
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
    }

    public int getEpicId() {
        return epicId;
    }

    @Override
    public int getTaskId() {
        return taskId;
    }

    @Override
    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }
}
