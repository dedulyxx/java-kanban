package ru.practicum.java.manager;

import ru.practicum.java.tasks.Task;

import java.util.List;

public interface HistoryManager {
    void add(Task task);

    void remove(Integer id);

    void clear();

    List<Task> getHistory();

    List<Task> getTasksInList();
}
