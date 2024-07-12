package ru.practicum.java.manager;

import org.junit.jupiter.api.Test;
import ru.practicum.java.tasks.Epic;
import ru.practicum.java.tasks.Subtask;
import ru.practicum.java.tasks.Task;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileBackedTaskManagerTest {

    @Test
    void loadEmptyFile() throws IOException {
        FileBackedTaskManager fileBackedTaskManager = FileBackedTaskManager.loadFromFile(Paths.get("C:\\java-kanban\\tasks.txt").toFile());
        assertEquals(fileBackedTaskManager, fileBackedTaskManager);
    }

    @Test
    void saveTasksInFile() throws IOException {
        TaskManager fileManager = new FileBackedTaskManager(Paths.get("C:\\java-kanban\\tasks.txt").toFile());
        Task taskOne = new Task("Задача 1", "задача 1 - описание");
        Epic epic = new Epic("Epic 1", "Epic 1 - description");
        Subtask subtask = new Subtask("Subtask 1", "Subtask 1", 1);
        fileManager.addTask(taskOne);
        fileManager.addEpic(epic);
        fileManager.addNewSubtask(subtask);
        BufferedReader br = new BufferedReader(new FileReader("C:\\java-kanban\\tasks.txt"));
        String line;
        int i = 0;
        while ((line = br.readLine()) != null) {
            i++;
        }
        assertEquals(4, i);
    }

    @Test
    void loadTasksFromFile() throws IOException {
        FileBackedTaskManager fileBackedTaskManager = FileBackedTaskManager.loadFromFile(Paths.get("C:\\java-kanban\\tasks.txt").toFile());
        BufferedReader br = new BufferedReader(new FileReader("C:\\java-kanban\\tasks.txt"));
        String line;
        int i = 0;
        while ((line = br.readLine()) != null) {
            i++;
        }
        assertEquals(4, i);
    }
}