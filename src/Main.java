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

        taskThree.setStatus(Status.IN_PROGRESS);
        taskManager.updateSubTask(subtask.setStatus(Status.DONE));
        taskManager.updateSubTask(subtask2.setStatus(Status.DONE));
        taskManager.viewTasks();
        System.out.println();
        System.out.println("....................Тест 3....................");
        taskManager.removeTaskById(1);

        taskManager.updateSubTask(subtask3.setStatus(Status.DONE));
        taskManager.viewTasks();
        taskManager.removeSubtask(subtask3);
        taskManager.viewTasks();

        System.out.println();
        System.out.println("....................Тест 4....................");
        taskManager.addNewSubtask(subtask4);
        taskManager.getTaskById(2);
        taskTwo.setStatus(Status.DONE);
        taskManager.getTaskById(2);
        taskManager.getTaskById(3);
        taskManager.getTaskById(2);
        taskManager.getTaskById(2);
        taskManager.getTaskById(2);
        taskManager.getTaskById(2);
        taskManager.getTaskById(2);
        taskManager.getSubtasksByEpic(epic);
        taskManager.getSubtasksByEpic(epic);
        taskManager.getSubtasksByEpic(epic);
        taskManager.getEpicById(1);
        taskManager.getEpicById(1);
        taskManager.getEpicById(2);

        System.out.println();
        System.out.println("....................Тест 5....................");
        taskManager.getHistory();

        System.out.println();
        System.out.println("....................Тест 6....................");
        taskManager.removeAllTasks();
        taskManager.addTask(taskOne);
        taskManager.addTask(taskTwo);
        taskManager.addTask(taskThree);
        taskManager.addEpic(epic);
        taskManager.addNewSubtask(subtask);
        taskManager.addNewSubtask(subtask2);
        Subtask subtaskThree = new Subtask("Subtask 3", "Subtask 3", 1);
        taskManager.addNewSubtask(subtaskThree);
        taskManager.addEpic(epicTwo);
        taskManager.getTaskById(10);
        taskManager.getHistory();
        System.out.println("-".repeat(60));
        taskManager.getTaskById(11);
        taskManager.getHistory();
        System.out.println("-".repeat(60));
        taskManager.getTaskById(12);
        taskManager.getHistory();
        System.out.println("-".repeat(60));
        taskManager.getTaskById(11);
        taskManager.getHistory();
        System.out.println("-".repeat(60));
        taskManager.getTaskById(10);
        taskManager.getHistory();
        System.out.println("-".repeat(60));
        taskManager.getTaskById(10);
        taskManager.getHistory();
        System.out.println("-".repeat(60));
        taskManager.getSubtasksById(14);
        taskManager.getHistory();
        System.out.println("-".repeat(60));
        taskManager.getSubtasksById(15);
        taskManager.getHistory();
        System.out.println("-".repeat(60));
        taskManager.getSubtasksById(14);
        taskManager.getHistory();
        System.out.println("-".repeat(60));
        taskManager.getSubtasksById(16);
        taskManager.getHistory();
        System.out.println("-".repeat(60));
        taskManager.getEpicById(1);
        taskManager.getHistory();
        System.out.println("-".repeat(60));
        taskManager.getEpicById(2);
        taskManager.getHistory();
        System.out.println("-".repeat(60));
        taskManager.getEpicById(1);
        taskManager.getHistory();
        System.out.println("-".repeat(60));
        taskManager.removeTaskById(11);
        taskManager.getHistory();
        System.out.println("-".repeat(60));
        taskManager.removeEpicById(1);
        taskManager.getHistory();
    }
}
