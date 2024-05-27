package ru.practicum.java.tasks;

import java.util.ArrayList;
import java.util.List;

public class Epic extends Task {

    private List<Integer> subtasksId = new ArrayList<>();

    public Epic(String nameTask, String description) {
        super(nameTask, description);
    }

    @Override
    public Epic setStatus(Status newStatus) {
        super.setStatus(newStatus);
        return this;
    }

    public void addSubtaskId(Integer id) {
        subtasksId.add(id);
    }

    @Override
    public String toString() {
        return " Эпик { " +
                "nameTask='" + nameTask + '\'' +
                ", description=" + description +
                ", status=" + status +
                '}';
    }

    @Override
    public int getId() {
        return id;
    }

    public List<Integer> getSubtasksId() {
        return subtasksId;
    }

    public void removeSubtask(Integer i) {
        subtasksId.remove(i);
    }

    public void clearSubtaskId() {
        subtasksId.clear();
    }
}
