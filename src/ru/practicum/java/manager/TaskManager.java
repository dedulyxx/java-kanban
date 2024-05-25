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

    private static int taskId;

    private static int epicId;
    public final Map<Integer, Task> tasks = new HashMap<>();
    public final Map<Integer, Epic> epics = new HashMap<>();
    public final Map<Integer, List<Subtask>> subTasks = new HashMap<>();
    private List<Subtask> tempSubTasks = new ArrayList<>();

    private List<Status> statusTask = new ArrayList<>();


    public void addTask(Task task) {                                       //Создание задачи
        task.setTaskId(++taskId);
        tasks.put(task.getTaskId(), task);
    }

    public void addEpic(Epic epic) {                                       //Создание Эпика
        epic.setTaskId(++taskId);
        epic.setEpicId(++epicId);
        epics.put(epic.getEpicId(), epic);
    }

    public void addSubTasks(Subtask subtask) {                   //Создание Подзадачи для Эпика
        Epic epic = getEpic(subtask, epics);
        subtask.setTaskId(++taskId);
        if (!subTasks.isEmpty() && subTasks.containsKey(epic.getEpicId())) {
            tempSubTasks = subTasks.get(epic.getEpicId());
            tempSubTasks.add(subtask);
            subTasks.put(epic.getEpicId(), tempSubTasks);
        } else {
            tempSubTasks = new ArrayList<>();
            tempSubTasks.add(subtask);
            subTasks.put(epic.getEpicId(), tempSubTasks);
        }
        checkTasks(epic, subTasks);
    }


    private Epic getEpic(Subtask subtask, Map<Integer, Epic> epics) {           //Получить Эпик по подзадаче
        Epic newEpic = null;
        for (Epic epic : epics.values()) {
            if (epic.getEpicId() == subtask.getEpicId()) {
                newEpic = epic;
                break;
            }
        }
        return newEpic;
    }

    private void checkTasks(Epic epic, Map<Integer, List<Subtask>> subTasks) {
        int countStatusDONE = 0;
        int countStatusNEW = 0;
        if (!subTasks.isEmpty()) {
            for (Subtask subTask : subTasks.get(epic.getEpicId())) {
                statusTask.add(subTask.getStatus());
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

    private void checkTask(Subtask subtask) {                         //Проверить статус таски
        Epic epic = getEpic(subtask, epics);
        checkTasks(epic, subTasks);
    }

    public Map viewTasks() {                                                //Вывод всех типов задач
        Map<Integer, List<Subtask>> viewTasks = new HashMap<>();
        if (!subTasks.isEmpty()) {
            viewTasks = subTasks;
        } else {
            System.out.println("Подзадач нет");
        }
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
        return viewTasks;
    }

    private void viewEpics() {                                               //Вывод Эпиков
        System.out.println(epics);
    }

    private void viewTask() {                                    //Вывод Подзадач
        System.out.println(tasks);
    }

    public void removeAllTasks() {                                     //Удаление всех задач (задачи, эпики, подхадачи)
        tasks.clear();
        epics.clear();
        subTasks.clear();
    }

    public void getTaskById(int taskId) {                                //Получить таску по taskId
        if (tasks.containsKey(taskId)) {
            System.out.println(tasks.get(taskId));
        } else {
            System.out.println("Задачи с таким идентификатором нет!");
        }
    }

    public void getEpicById(int epicId) {                                //Получить Эпик по epicId
        if (epics.containsKey(epicId)) {
            System.out.println(epics.get(epicId));
        } else {
            System.out.println("Эпика с таким идентификатором нет!");
        }
    }

    public void getSubtasksByEpic(Epic epic) {                         //Получить Подзадачу по Эпику
        if (subTasks.isEmpty()) {
            System.out.println("Подзадач нет");
            return;
        }
        System.out.println(subTasks.get(epic.getEpicId()));
    }

    public void getSubtasksById(int epicId) {                             //Получение подзадачи по идентификатору
        if (subTasks.isEmpty()) {
            System.out.println("Подзадач нет");
            return;
        }
        for (Integer ep : subTasks.keySet()) {
            for (Subtask subTask : subTasks.get(ep)) {
                if (subTask.getEpicId() == epicId) {
                    System.out.println(subTask);
                    return;
                }
            }
        }
        System.out.println("Подзадачи с таким идентификатором нет!");
    }

    public void removeTaskById(int taskId) {                                    //Удалить задачу по taskId
        if (tasks.containsKey(taskId)) {
            tasks.remove(taskId);
        } else {
            System.out.println("Задачи с таким индентификатором нет");
        }
    }

    public void removeEpicById(int taskId) {                                    //Удалить Эпик по taskId
        if (epics.containsKey(taskId)) {
            epics.remove(taskId);
        } else {
            System.out.println("Задачи с таким индентификатором нет");
        }
    }

    public void removeSubtaskById(int taskId) {                                 //Удалить подзадачу по taskId
        if (!subTasks.isEmpty()) {
            for (Integer ep : subTasks.keySet()) {
                tempSubTasks = subTasks.get(ep);
                for (Subtask subTask : tempSubTasks) {
                    if (subTask.getTaskId() == taskId) {
                        tempSubTasks.remove(subTask);
                        subTasks.put(ep, tempSubTasks);
                        Epic epic = getEpic(subTask, epics);
                        checkTasks(epic, subTasks);
                        return;
                    }
                }
            }
        } else {
            System.out.println("Задачи с таким индентификатором нет");
        }
    }


    public void removeSubtask(Epic epic, Subtask subtask) {                  //Удалить подзадачу
        tempSubTasks = subTasks.get(subtask.getEpicId());
        for (Subtask tempSubtask : tempSubTasks) {
            if (tempSubtask.getEpicId() == subtask.getEpicId()) {
                tempSubTasks.remove(tempSubtask);
                break;
            }
        }
        checkTasks(epic, subTasks);
    }

    public void removeEpic(Epic epic) {                           //Удалить Эпик
        epics.remove(epic.getEpicId());
        subTasks.remove(epic.getEpicId());
    }

    public void updateTask(Task task) {                                      //Обновить таску
        tasks.put(task.getTaskId(), task);
    }

    public void updateEpic(Epic epic) {                                      //Обновить Эпик
        epics.put(epic.getEpicId(), epic);
        checkTasks(epic, subTasks);
    }

    public Subtask updateSubTask(int epicId, Subtask subtask) {                             //Обновить Подзадачу
        tempSubTasks = subTasks.get(epicId);
        for (Subtask subtasks : tempSubTasks) {
            if (subtasks.getTaskId() == subtask.getTaskId()) {
                tempSubTasks.remove(subtasks);
                break;
            }
        }
        tempSubTasks.add(subtask);
        subTasks.put(epicId, tempSubTasks);
        checkTask(subtask);
        return subtask;
    }
}
