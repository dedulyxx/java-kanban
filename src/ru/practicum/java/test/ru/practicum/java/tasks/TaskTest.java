package ru.practicum.java.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    Task task = new Task("Задача 1", "задача 1 - описание");
    Task taskTwo = new Task("Задача 1", "задача 1 - описание");

    @Test
    void CheckStatusWhenSetStatusisDone() {
        assertEquals(task.status.isNew, task.getStatus());
        task.setStatus(Status.isDone);
        assertEquals(task.status.isDone, task.getStatus());
    }

    @Test
    void equalsWhenSetOneId() {
        taskTwo.setTaskId(0);
        assertEquals(taskTwo, task);
    }

    @Test
    void getIdWhenCreateTask() {
        assertEquals(0, task.getId());
    }

}