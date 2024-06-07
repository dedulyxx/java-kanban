import ru.practicum.java.manager.InMemoryTaskManager;
import ru.practicum.java.manager.Managers;
import ru.practicum.java.manager.TaskManager;
import ru.practicum.java.tasks.*;

public class Main {

    public static void main(String[] args) {

        TaskManager taskManager = Managers.getDefault();

        Task taskOne = new Task("Задача 1", "задача 1 - описание");
        Task taskTwo = new Task("Задача 2", "задача 2 - описание");
        Task taskThree = new Task("Задача 3", "задача 3 - описание");
        Epic epic = new Epic("Epic 1", "Epic 1 - description");
        Subtask subtask = new Subtask("Subtask 1", "Subtask 1", 1);
        Subtask subtask2 = new Subtask("Subtask 2", "Subtask 2", 1);
        Epic epicTwo = new Epic("Epic 2", "Epic 2 - description");
        Subtask subtask3 = new Subtask("Subtask 3", "Subtask 3", 2);
        Subtask subtask4 = new Subtask("Subtask 4", "Subtask 4", 2);


        System.out.println();
        System.out.println("....................Тест 1....................");
        taskManager.addTask(taskOne);
        taskManager.addTask(taskTwo);
        taskManager.addTask(taskThree);
        taskManager.addEpic(epic);
        taskManager.addNewSubtask(subtask);
        taskManager.addNewSubtask(subtask2);
        taskManager.addEpic(epicTwo);
        taskManager.addNewSubtask(subtask3);
        taskManager.viewTasks();
        System.out.println();

        System.out.println("....................Тест 2....................");
        taskOne.setStatus(Status.IN_PROGRESS);
        taskTwo.setStatus(Status.DONE);
        taskThree.setStatus(Status.IN_PROGRESS);
        taskManager.updateSubTask(subtask.setStatus(Status.DONE));
        taskManager.updateSubTask(subtask2.setStatus(Status.DONE));
        taskManager.viewTasks();

        System.out.println();
        System.out.println("....................Тест 3....................");
        taskManager.removeTaskById(1);
        taskManager.removeEpic(epic);
        taskManager.updateSubTask(subtask3.setStatus(Status.DONE));
        taskManager.viewTasks();
        taskManager.removeSubtask(subtask3);
        taskManager.viewTasks();

        System.out.println();
        System.out.println("....................Тест 4....................");
        taskManager.addNewSubtask(subtask4);
        taskManager.getTaskById(2);
        taskManager.getTaskById(3);
        taskManager.getEpicById(2);
        taskManager.getTaskById(2);
        taskManager.getTaskById(3);
        taskManager.getEpicById(2);
        taskManager.getEpicById(2);
        taskManager.getEpicById(2);
        taskManager.getEpicById(2);
        taskManager.getSubtasksById(1);
        taskManager.getTaskById(2);
        taskManager.getEpicById(2);

        System.out.println();
        System.out.println("....................Тест 5....................");
        taskManager.getHistory();


    }
}
