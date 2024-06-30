package ru.practicum.java.tasks;

import org.junit.jupiter.api.Test;
import ru.practicum.java.manager.Managers;
import ru.practicum.java.manager.TaskManager;

import static org.junit.jupiter.api.Assertions.*;

class SubtaskTest {

    TaskManager taskManager = Managers.getDefault();

    Epic epic = new Epic("Epic 1", "Epic 1 - description");
    Subtask subtask = new Subtask("Subtask 1", "Subtask 1", 1);
    Subtask subtask2 = new Subtask("Subtask 2", "Subtask 2", 1);

    @Test
    void checkStatusWhenSetStatusDONE() {
        assertEquals(subtask.status.NEW, subtask.getStatus());
        subtask.setStatus(Status.DONE);
        assertEquals(subtask.status.DONE, subtask.getStatus());
    }

    @Test
    void equalsWhenSetOneId() {
        taskManager.addEpic(epic);
        taskManager.addNewSubtask(subtask);
        taskManager.addNewSubtask(subtask2);
        subtask2.setTaskId(2);
        assertEquals(subtask2, subtask);
    }

    @Test
    void getEpicIdWhenAdDONEEpic() {
        assertEquals(1, subtask.getEpicId());
    }

}