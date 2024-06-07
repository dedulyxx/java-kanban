package ru.practicum.java.manager;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import ru.practicum.java.tasks.Epic;
import ru.practicum.java.tasks.Subtask;
import ru.practicum.java.tasks.Task;

import java.util.ArrayList;
import java.util.List;

class InMemoryHistoryManagerTest {

    TaskManager taskManager = Managers.getDefault();
    InMemoryHistoryManager historyManager = new InMemoryHistoryManager();

    Task taskOne = new Task("Задача 1", "задача 1 - описание");
    Task taskTwo = new Task("Задача 2", "задача 2 - описание");
    Task taskthree = new Task("Задача 3", "задача 3 - описание");
    Epic epic = new Epic("Epic 1", "Epic 1 - description");
    Subtask subtask = new Subtask("Subtask 1", "Subtask 1", 1);
    Subtask subtask2 = new Subtask("Subtask 2", "Subtask 2", 1);
    Epic epicTwo = new Epic("Epic 2", "Epic 2 - description");
    Subtask subtask3 = new Subtask("Subtask 3", "Subtask 3", 2);
    Subtask subtask4 = new Subtask("Subtask 4", "Subtask 4", 2);

    @Test
    void add() {
        taskManager.addTask(taskOne);
        taskManager.addTask(taskTwo);
        taskManager.addTask(taskthree);
        taskManager.addEpic(epic);
        taskManager.addNewSubtask(subtask);
        taskManager.addNewSubtask(subtask2);
        taskManager.addEpic(epicTwo);
        taskManager.addNewSubtask(subtask3);
        taskManager.addNewSubtask(subtask4);
        historyManager.add(taskOne);
        historyManager.add(epicTwo);
        historyManager.add(taskthree);
        historyManager.add(epicTwo);
        historyManager.add(subtask4);
        historyManager.add(taskOne);
        historyManager.add(subtask4);
        historyManager.add(subtask3);
        historyManager.add(subtask4);
        historyManager.add(subtask3);
        historyManager.add(subtask3);
        final List<Task> history = historyManager.getHistory();
        assertNotNull(history, "История пустая.");
        assertEquals(10, history.size());
    }

    @Test
    void getHistory() {
        final List<Task> history = historyManager.getHistory();
        assertEquals(0, history.size());
        historyManager.add(taskOne);
        assertEquals(1, history.size());
    }
}