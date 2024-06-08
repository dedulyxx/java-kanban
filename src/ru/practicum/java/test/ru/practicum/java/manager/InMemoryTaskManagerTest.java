package ru.practicum.java.manager;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import ru.practicum.java.tasks.Epic;
import ru.practicum.java.tasks.Status;
import ru.practicum.java.tasks.Subtask;
import ru.practicum.java.tasks.Task;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

class InMemoryTaskManagerTest {

    TaskManager taskManager = Managers.getDefault();
    HistoryManager historyManager = Managers.getDefaultHistory();

    Task task = new Task("Задача 1", "задача 1 - описание");
    Task taskTwo = new Task("Задача 2", "задача 2 - описание");
    Task taskThree = new Task("Задача 3", "задача 3 - описание");
    Epic epic = new Epic("Epic 1", "Epic 1 - description");
    Subtask subtask = new Subtask("Subtask 1", "Subtask 1", 1);
    Subtask subtask2 = new Subtask("Subtask 2", "Subtask 2", 1);
    Epic epicTwo = new Epic("Epic 2", "Epic 2 - description");
    Subtask subtask3 = new Subtask("Subtask 3", "Subtask 3", 2);
    Subtask subtask4 = new Subtask("Subtask 4", "Subtask 4", 2);

    @Test
    void addNewTask() {
        final int taskId = taskManager.addTask(task);
        final Task savedTask = taskManager.getTaskById(taskId);

        assertNotNull(savedTask, "Задача не найдена.");
        assertEquals(task, savedTask, "Задачи не совпадают.");

        final Map<Integer, Task> tasks = taskManager.getTasks();

        assertNotNull(tasks, "Задачи не возвращаются.");
        assertEquals(1, tasks.size(), "Неверное количество задач.");
        assertEquals(task, tasks.get(1), "Задачи не совпадают.");
    }

    @Test
    void addNewEpic() {
        final int taskId = taskManager.addEpic(epic);
        final Epic savedEpic = taskManager.getEpicById(taskId);

        assertNotNull(savedEpic, "Эпик не найден.");
        assertEquals(epic, savedEpic, "Эпики не совпадают.");
    }

    @Test
    void addNewSubtask() {
        taskManager.addEpic(epic);
        taskManager.addNewSubtask(subtask);
        final Subtask savedTask = taskManager.getSubtasksById(subtask.getId());

        assertNotNull(savedTask, "Задача не найдена.");
        assertEquals(subtask, savedTask, "Задачи не совпадают.");
    }

    @Test
    void shouldReturnTaskWhenTaskIsExists() {
        taskManager.addTask(taskTwo);
        int id = taskTwo.getId();
        Task task = taskManager.getTaskById(id);
        assertEquals(taskTwo,task);
    }

    @Test
    void shouldReturnEpicWhenEpicIsExists() {
        taskManager.addEpic(epic);
        int id = epic.getEpicId();
        Epic ep = taskManager.getEpicById(id);
        assertEquals(ep,epic);
    }

    @Test
    void shouldReturnSubtaskByEpicWhenSubtaskIsExists() {
        taskManager.addEpic(epic);
        taskManager.addNewSubtask(subtask);
        assertEquals(taskManager.getSubtasksByEpic(epic), subtask);
    }

    @Test
    void shouldReturnSubtaskByIdWhenSubtaskIsExists() {
        taskManager.addEpic(epic);
        taskManager.addNewSubtask(subtask);
        int id = subtask.getId();
        Subtask sub = taskManager.getSubtasksById(id);
        assertEquals(sub,subtask);
    }

    @Test
    void viewTasksWhenAllTasksAreExist() {
        taskManager.addTask(taskThree);
        taskManager.addNewSubtask(subtask2);
        taskManager.addEpic(epicTwo);

        taskManager.viewTasks();
        final Map<Integer, Task> tasks = taskManager.getTasks();
        final Map<Integer, Epic> epics = taskManager.getEpics();
        final Map<Integer, Subtask> subtasks = taskManager.getSubTasks();
        List<Map<Integer, ? extends Task>> list = new ArrayList<>();
        list.add(tasks);
        list.add(epics);
        list.add(subtasks);
        assertEquals(taskManager.viewTasks(), list,"Списки не равны");
    }

    @Test
    void getHistoryWhenHistorySizeIsOne() {
        final List<Task> history = taskManager.getHistory();
        assertEquals(0, history.size());
        historyManager.add(task);
        taskManager.getTaskById(1);
        assertEquals(1, history.size());
    }

    @Test
    void getEmptyListWhenRemoveAllTasks() {
        taskManager.removeAllTasks();
        final int tasks = taskManager.getTasks().size();
        final int epics = taskManager.getEpics().size();
        final int subtasks = taskManager.getSubTasks().size();
        assertEquals(0, tasks,"Список не пуст");
        assertEquals(0, epics,"Список не пуст");
        assertEquals(0, subtasks,"Список не пуст");
    }

    @Test
    void CheckForNullWhenRemoveTaskById() {
        taskManager.addTask(taskThree);
        taskManager.removeTaskById(1);
        Map<Integer, Task> tasks = taskManager.getTasks();
        assertNull(tasks.get(taskThree.getId()));
    }

    @Test
    void CheckForNullWhenRemoveEpicById() {
        taskManager.addEpic(epic);
        taskManager.removeEpicById(1);
        Map<Integer, Epic> epics = taskManager.getEpics();
        assertNull(epics.get(epic.getId()));
    }

    @Test
    void CheckForNullWhenRemoveSubtaskById() {
        taskManager.addEpic(epicTwo);
        taskManager.addNewSubtask(subtask2);
        taskManager.removeSubtaskById(2);
        Map<Integer, Subtask> subtasks = taskManager.getSubTasks();
        assertNull(subtasks.get(subtask4.getId()));
    }

    @Test
    void CheckForNullWhenRemoveSubtask() {
        taskManager.addEpic(epicTwo);
        taskManager.addNewSubtask(subtask2);
        taskManager.removeSubtask(subtask2);
        Map<Integer, Subtask> subtasks = taskManager.getSubTasks();
        assertNull(subtasks.get(subtask4.getId()));
    }

    @Test
    void CheckForNullWhenRemoveEpic() {
        taskManager.addEpic(epicTwo);
        taskManager.addNewSubtask(subtask2);
        taskManager.addNewSubtask(subtask3);
        taskManager.removeEpic(epicTwo);
        Map<Integer, Epic> epics = taskManager.getEpics();
        assertNull(epics.get(epicTwo.getId()));
        Map<Integer, Subtask> subtasks = taskManager.getSubTasks();
        assertNull(subtasks.get(subtask2.getId()));
        assertNull(subtasks.get(subtask3.getId()));
    }

    @Test
    void updateTaskWhenSetNewStatus() {
        taskManager.addTask(taskThree);
        assertEquals(Status.NEW,taskThree.getStatus());
        taskThree.setStatus(Status.DONE);
        taskManager.updateTask(taskThree);
        Map<Integer, Task> tasks = taskManager.getTasks();
        assertEquals(tasks.get(taskThree.getId()).getStatus(),Status.DONE);
    }

    @Test
    void updateEpicWhenSetNewStatus() {
        taskManager.addEpic(epic);
        assertEquals(Status.NEW,epic.getStatus());
        epic.setStatus(Status.IN_PROGRESS);
        taskManager.updateEpic(epic);
        Map<Integer, Epic> epics = taskManager.getEpics();
        assertEquals(epics.get(epic.getEpicId()).getStatus(),Status.NEW);
    }

    @Test
    void updateSubtaskWhenSetNewStatus() {
        taskManager.addEpic(epic);
        taskManager.addNewSubtask(subtask2);
        assertEquals(Status.NEW,subtask2.getStatus());
        subtask2.setStatus(Status.IN_PROGRESS);
        taskManager.updateSubTask(subtask2);
        Map<Integer, Subtask> subtasks = taskManager.getSubTasks();
        assertEquals(subtasks.get(subtask2.getId()).getStatus(),Status.IN_PROGRESS);
        Map<Integer, Epic> epics = taskManager.getEpics();
        assertEquals(epics.get(epic.getEpicId()).getStatus(),Status.IN_PROGRESS);
    }

    @Test
    void getInstanceByManagers() {
        TaskManager taskManager = Managers.getDefault();
        HistoryManager historyManager = Managers.getDefaultHistory();
        assertEquals(taskManager, taskManager);
        assertEquals(historyManager, historyManager);
    }

    @Test
    void checkEqualsTasksWhenSetOneTaskId() {
        taskManager.addTask(task);
        taskManager.addTask(taskTwo);
        taskTwo.setTaskId(1);
        assertEquals(task,taskTwo);
    }
}