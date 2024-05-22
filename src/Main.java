public class Main {

    public static void main(String[] args) {

        TaskManager taskManager = new TaskManager();

        Task task = new Task("Задача", "задача 1 - описание");
        Epic epic = new Epic("Epic 1", "Epic 1 - description");
        Subtask subtask = new Subtask("Subtask 1", "Subtask 1", 1);
        Subtask subtask2 = new Subtask("Subtask 2", "Subtask 2", 1);
        Epic epic2 = new Epic("Epic 2", "Epic 2 - description");
        Epic epic3 = new Epic("Epic 3", "Epic 3 - description");
        Subtask subtask3 = new Subtask("Subtask 3", "Subtask 3",3);
        Epic epic4 = new Epic("Epic4", "Epic2 - description");



        taskManager.addTask(task);
        task.setStatus(Status.DONE);
        taskManager.addEpic(epic);
        taskManager.addSubTasks(subtask);
        taskManager.addSubTasks(subtask2);
        taskManager.viewTasks();
        subtask.setStatus(Status.IN_PROGRESS);
        taskManager.viewTasks();
        subtask.setStatus(Status.DONE);
        taskManager.viewTasks();
        taskManager.addEpic(epic2);
        taskManager.addEpic(epic3);
        taskManager.addSubTasks(subtask3);
        taskManager.viewTasks();
        taskManager.removeEpic(epic3);
        taskManager.viewTasks();
        taskManager.removeEpic(epic2);
        taskManager.viewTasks();
    }
}
