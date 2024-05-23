package ru.practicum.java.tasks;

import java.util.Map;

public class Subtask extends Task {

    int epicId;

    public Subtask(String nameTask, String description, int epicId) {
        super(nameTask, description);
        this.epicId = epicId;
    }


    @Override
    public void setStatus(Enum Status) {
        super.setStatus(Status);
        TaskManager.checkTask(this);
    }


    //Получить Эпик по подзадаче
    public static Epic getEpic(Subtask subtask, Map<Integer, Epic> epics) {
        Epic newEpic = null;
        for (Epic epic : epics.values()) {
            if (epic.epicId == subtask.epicId) {
                newEpic = epic;
                break;
            }
        }
        return newEpic;
    }


    @Override
    public String toString() {
        return " Подзадача " + uuid + " {" +
                " nameTask='" + nameTask + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
