package ru.practicum.java.manager;

import org.junit.jupiter.api.Test;
import ru.practicum.java.tasks.Epic;
import ru.practicum.java.tasks.Subtask;
import ru.practicum.java.tasks.Task;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;

import static org.junit.jupiter.api.Assertions.assertEquals;

class FileBackedTaskManagerTest {

    private Path path = Paths.get("./count.txt");

    @Test
    void loadEmptyFile() throws IOException {
        FileBackedTaskManager fileBackedTaskManager = FileBackedTaskManager.loadFromFile(path.toFile());
        assertEquals(fileBackedTaskManager, fileBackedTaskManager);
    }

    @Test
    void saveTasksInFile() throws IOException {
        TaskManager fileManager = new FileBackedTaskManager(path.toFile());
        Task taskOne = new Task("Задача 1", "задача 1 - описание");
        Epic epic = new Epic("Epic 1", "Epic 1 - description");
        Subtask subtask = new Subtask("Subtask 1", "Subtask 1", 1);
        fileManager.addTask(taskOne);
        fileManager.addEpic(epic);
        fileManager.addNewSubtask(subtask);
        BufferedReader br = new BufferedReader(new FileReader("./count.txt"));
        int i = 0;
        while ((br.readLine()) != null) {
            i++;
        }
        assertEquals(4, i);
    }

    @Test
    void loadTasksFromFile() throws IOException {
        FileBackedTaskManager.loadFromFile(path.toFile());
        BufferedReader br = new BufferedReader(new FileReader("./count.txt"));
        int i = 0;
        while (br.readLine() != null) {
            i++;
        }
        assertEquals(4, i);
    }
}