package ru.practicum.java.manager;

import org.junit.jupiter.api.Test;
import ru.practicum.java.tasks.Epic;
import ru.practicum.java.tasks.Subtask;
import ru.practicum.java.tasks.Task;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileBackedTaskManagerTest {

    File file = Paths.get("/home/tasks.txt").toFile();

    @Test
    void loadEmptyFile() throws IOException {
        FileBackedTaskManager fileBackedTaskManager = FileBackedTaskManager.loadFromFile(file);
        assertEquals(fileBackedTaskManager, fileBackedTaskManager);
    }

    @Test
    void saveTasksInFile() throws IOException {
        TaskManager fileManager = new FileBackedTaskManager(file);
        Task taskOne = new Task("Задача 1", "задача 1 - описание");
        Epic epic = new Epic("Epic 1", "Epic 1 - description");
        Subtask subtask = new Subtask("Subtask 1", "Subtask 1", 1);
        fileManager.addTask(taskOne);
        fileManager.addEpic(epic);
        fileManager.addNewSubtask(subtask);
        BufferedReader br = new BufferedReader(new FileReader("/home/tasks.txt"));
        String line;
        int i = 0;
        while ((line = br.readLine()) != null) {
            i++;
        }
        assertEquals(4, i);
    }

    @Test
    void loadTasksFromFile() throws IOException {
        FileBackedTaskManager.loadFromFile(file);
        BufferedReader br = new BufferedReader(new FileReader("/home/tasks.txt"));
        int i = 0;
        while (br.readLine() != null) {
            i++;
        }
        assertEquals(4, i);
    }
}