package ru.practicum.java.manager;

import ru.practicum.java.tasks.Epic;
import ru.practicum.java.tasks.Status;
import ru.practicum.java.tasks.Subtask;
import ru.practicum.java.tasks.Task;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskManager {

    private int taskId;
    private int epicId;
    public final Map<Integer, Task> tasks = new HashMap<>();
    public final Map<Integer, Epic> epics = new HashMap<>();
    public final Map<Integer, Subtask> subTasks = new HashMap<>();

    private List<Status> statusTask = new ArrayList<>();


    public int addTask(Task task) {                                       //Создание задачи
        task.setTaskId(++taskId);
        tasks.put(task.getId(), task);
        return taskId;
    }

    public int addEpic(Epic epic) {                                       //Создание Эпика
        epic.setTaskId(++epicId);
        epics.put(epic.getId(), epic);
        return taskId;
    }

    public Integer addNewSubtask(Subtask subtask) {                      //Создание Подзадачи
        final int epicId = subtask.getEpicId();
        Epic epic = epics.get(epicId);
        if (epic == null) {
            return null;
        }
        final int id = subTasks.size() + 1;
        subtask.setTaskId(id);
        subTasks.put(id, subtask);
        epic.addSubtaskId(subtask.getId());
        checkTasks(epicId);
        return id;
    }

    private void checkTasks(Integer epicId) {
        Epic epic = epics.get(epicId);
        int countStatusDONE = 0;
        int countStatusNEW = 0;
        if (!subTasks.isEmpty()) {
            for (Subtask subTask : subTasks.values()) {
                if (subTask.getEpicId() == epicId) {
                    statusTask.add(subTask.getStatus());
                }
            }
            if (statusTask.isEmpty()) {
                epic.setStatus(Status.NEW);
                return;
            }
            for (Status status : statusTask) {
                if (status == Status.IN_PROGRESS) {
                    epic.setStatus(Status.IN_PROGRESS);
                    statusTask.clear();
                    return;
                } else if (status == Status.DONE) {
                    countStatusDONE++;
                } else if (status == Status.NEW) {
                    countStatusNEW++;
                }
            }
            if (countStatusDONE == statusTask.size()) {
                epic.setStatus(Status.DONE);
                statusTask.clear();
            } else if (countStatusNEW == statusTask.size()) {
                epic.setStatus(Status.NEW);
                statusTask.clear();
            } else {
                epic.setStatus(Status.IN_PROGRESS);
                statusTask.clear();
            }
        } else {
            epic.setStatus(Status.NEW);
        }
    }

    public void viewTasks() {                                        //Вывод всех типов задач
        if (!tasks.isEmpty()) {
            viewTask();
        } else {
            System.out.println("Задач нет");
        }
        if (!epics.isEmpty()) {
            viewEpics();
        } else {
            System.out.println("Эпиков нет");
        }
        if (!subTasks.isEmpty()) {
            viewSubTasks();
        } else {
            System.out.println("Подзадач нет");
        }
    }

    private void viewEpics() {                                               //Вывод Эпиков
        System.out.println(epics);
    }

    private void viewTask() {                                    //Вывод задач
        System.out.println(tasks);
    }

    private void viewSubTasks() {                                    //Вывод Подзадач
        System.out.println(subTasks);
    }

    public void removeAllTasks() {                                     //Удаление всех задач (задачи, эпики, подхадачи)
        tasks.clear();
        epics.clear();
        subTasks.clear();
    }

    public Task getTaskById(int taskId) {                                //Получить таску по taskId
        if (tasks.containsKey(taskId)) {
            System.out.println(tasks.get(taskId));
        } else {
            System.out.println("Задачи с таким идентификатором нет!");
        }
        return tasks.get(taskId);
    }

    public Epic getEpicById(int epicId) {                                //Получить Эпик по epicId
        if (epics.containsKey(epicId)) {
            System.out.println(epics.get(epicId));
        } else {
            System.out.println("Эпика с таким идентификатором нет!");
        }
        return epics.get(epicId);
    }

    public Subtask getSubtasksByEpic(Epic epic) {                         //Получить Подзадачу по Эпику
        if (subTasks.isEmpty()) {
            System.out.println("Подзадач нет");
            return null;
        }
        List<Integer> subtasksId = epic.getSubtasksId();
        for (Integer subtaskId : subtasksId) {
            System.out.println(subTasks.get(subtaskId));
            return subTasks.get(subtaskId);
        }
        return null;
    }

    public Subtask getSubtasksById(int id) {                             //Получение подзадачи по идентификатору
        if (subTasks.isEmpty()) {
            System.out.println("Подзадач нет");
            return null;
        }
            for (Subtask subTask : subTasks.values()) {
                if (subTask.getId() == id) {
                    System.out.println(subTask);
                    return subTask;
                }
            }
        System.out.println("Подзадачи с таким идентификатором нет!");
            return null;
    }

    public void removeTaskById(int taskId) {                                    //Удалить задачу по taskId
        if (tasks.containsKey(taskId)) {
            tasks.remove(taskId);
        } else {
            System.out.println("Задачи с таким индентификатором нет");
        }
    }

    public void removeEpicById(int id) {                                    //Удалить Эпик по id
        if (epics.containsKey(id)) {
            Epic epic = epics.get(id);
            removeEpic(epic);

        } else {
            System.out.println("Задачи с таким индентификатором нет");
        }
    }

    public void removeSubtaskById(int id) {                                 //Удалить подзадачу по id
        if (!subTasks.isEmpty()) {
                for (Subtask subTask : subTasks.values()) {
                    if (subTask.getId() == id) {
                        subTasks.remove(subTask.getId());
                        checkTasks(subTask.getEpicId());
                        return;
                    }
                }
        } else {
            System.out.println("Задачи с таким индентификатором нет");
        }
    }


    public void removeSubtask(Subtask subtask) {                  //Удалить подзадачу
        for (Subtask tempSubtask : subTasks.values()) {
            if (tempSubtask.getId() == subtask.getId()) {
                subTasks.remove(tempSubtask.getId());
                checkTasks(tempSubtask.getEpicId());
                Epic epic = epics.get(subtask.getEpicId());
                epic.removeSubtask(subtask.getId());
                break;
            }
        }
    }

    public void removeEpic(Epic epic) {                           //Удалить Эпик
        List<Integer> subtasksId = epic.getSubtasksId();
        for (Integer subtaskId : subtasksId) {
                subTasks.remove(subtaskId);
        }
        epic.clearSubtaskId();
        epics.remove(epic.getId());
    }

    public Task updateTask(Task task) {                                      //Обновить таску
        tasks.put(task.getId(), task);
        return task;
    }

    public Epic updateEpic(Epic epic) {                                      //Обновить Эпик
        if (epics.containsKey(epic.getId())) {
        epics.put(epic.getId(), epic);
        checkTasks(epic.getId());
        }
        return epic;
    }

    public Subtask updateSubTask(Subtask subtask) {                             //Обновить Подзадачу
        for (Subtask subtasks : subTasks.values()) {
            if (subtasks.getId() == subtask.getId()) {
                subTasks.replace(subtasks.getId(), subtasks);
                checkTasks(subtasks.getEpicId());
                break;
            }
        }
        return subtask;
    }
}
