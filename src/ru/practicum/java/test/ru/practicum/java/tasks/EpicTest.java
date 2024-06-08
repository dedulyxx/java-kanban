package ru.practicum.java.tasks;

import org.junit.jupiter.api.Test;
import ru.practicum.java.manager.Managers;
import ru.practicum.java.manager.TaskManager;

import static org.junit.jupiter.api.Assertions.*;

class EpicTest {

    TaskManager taskManager = Managers.getDefault();

    Epic epic = new Epic("Epic 1", "Epic 1 - description");
    Epic epic2 = new Epic("Epic 1", "Epic 1 - description");
    Subtask subtask = new Subtask("Subtask 1", "Subtask 1", 1);

    @Test
    void CheckStatusWhenSetStatusDone() {
        assertEquals(epic.status.NEW, epic.getStatus());
        epic.setStatus(Status.DONE);
        assertEquals(epic.status.DONE, epic.getStatus());
    }

    @Test
    void equalsWhenSetOneId() {
        taskManager.addEpic(epic);
        taskManager.addEpic(epic2);
        epic2.setTaskId(1);
        assertEquals(epic2, epic);
    }

    @Test
    void getEpicIdWhenAddOneEpic() {
        taskManager.addEpic(epic);
        assertEquals(1, epic.getEpicId());
    }

    @Test
    void getIdWhenAddOneEpic() {
        taskManager.addEpic(epic);
        assertEquals(1, epic.getId());
    }
}