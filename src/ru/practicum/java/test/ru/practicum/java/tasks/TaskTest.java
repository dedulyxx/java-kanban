package ru.practicum.java.tasks;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class TaskTest {

    Task task = new Task("Задача 1", "задача 1 - описание");
    Task taskTwo = new Task("Задача 1", "задача 1 - описание");

    @Test
    void CheckStatusWhenSetStatusisDONE() {
        assertEquals(task.status.isNEW, task.getStatus());
        task.setStatus(Status.isDONE);
        assertEquals(task.status.isDONE, task.getStatus());
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