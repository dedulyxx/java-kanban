import ru.practicum.java.tasks.*;

public class Main {

    public static void main(String[] args) {

        TaskManager taskManager = new TaskManager();

        Task taskOne = new Task("Задача1", "задача 2 - описание");
        Task taskTwo = new Task("Задача2", "задача 2 - описание");
        Task taskthree = new Task("Задача3", "задача 3 - описание");
        Epic epic = new Epic("Epic 1", "Epic 1 - description");
        Subtask subtask = new Subtask("Subtask 1", "Subtask 1", 1);
        Subtask subtask2 = new Subtask("Subtask 2", "Subtask 2", 1);
        Epic epicTwo = new Epic("Epic 2", "Epic 2 - description");
        Subtask subtask3 = new Subtask("Subtask 3", "Subtask 3",2);

        System.out.println();
        System.out.println("....................Тест 1....................");
        taskManager.addTask(taskOne);
        taskManager.addTask(taskTwo);
        taskManager.addTask(taskthree);
        taskManager.addEpic(epic);
        taskManager.addSubTasks(subtask);
        taskManager.addSubTasks(subtask2);
        taskManager.addEpic(epicTwo);
        taskManager.addSubTasks(subtask3);
        System.out.println(taskManager.viewTasks());
        System.out.println();

        System.out.println("....................Тест 2....................");
        taskOne.setStatus(Status.IN_PROGRESS);
        taskTwo.setStatus(Status.DONE);
        taskthree.setStatus(Status.IN_PROGRESS);
        subtask2.setStatus(Status.IN_PROGRESS);
        subtask3.setStatus(Status.DONE);
        System.out.println(taskManager.viewTasks());

        System.out.println();
        System.out.println("....................Тест 3....................");
        taskManager.removeById(1);
        taskManager.removeEpic(epic);
        System.out.println(taskManager.viewTasks());
    }
}
