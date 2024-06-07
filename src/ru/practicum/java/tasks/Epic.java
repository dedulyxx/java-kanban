package ru.practicum.java.tasks;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Epic extends Task {

    private List<Integer> subtasksId = new ArrayList<>();

    private int epicId;

    public Epic(String nameTask, String description) {
        super(nameTask, description);
    }

    public int getEpicId() {
        return epicId;
    }

    public void setEpicId(int epicId) {
        this.epicId = epicId;
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
    public boolean equals(Object object) {
        if (this == object) return true;
        if ((object.getClass() != this.getClass()) || object == null) return false;
        Epic epic = (Epic) object;
        return id == epic.id;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 53 * hash + ((nameTask == null) ? 0 : nameTask.hashCode()) + ((description == null) ? 0 : description.hashCode()) + id + epicId;
        return hash;
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

    @Override
    public void setTaskId(int id) {
        super.setTaskId(id);
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
