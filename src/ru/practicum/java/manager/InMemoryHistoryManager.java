package ru.practicum.java.manager;

import ru.practicum.java.tasks.Task;

import java.util.ArrayList;
import java.util.List;

public class InMemoryHistoryManager implements HistoryManager {

    private static final int SIZE = 10;
    private final List<Task> historyList = new ArrayList<>();

    @Override
    public void add(Task task) {
        if (historyList.size() == SIZE) {
            historyList.removeFirst();
            historyList.add(task);
        } else {
            historyList.add(task);
        }
    }

    @Override
    public List<Task> getHistory() {
        for (Task list : historyList) {
            System.out.println(list);
        }
        return historyList;
    }
}
