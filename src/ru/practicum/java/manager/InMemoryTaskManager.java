package ru.practicum.java.manager;

import ru.practicum.java.tasks.Epic;
import ru.practicum.java.tasks.Status;
import ru.practicum.java.tasks.Subtask;
import ru.practicum.java.tasks.Task;

import java.util.*;

public class InMemoryTaskManager implements TaskManager {

    private int taskId;
    private final Map<Integer, Task> tasks = new HashMap<>();
    private final Map<Integer, Epic> epics = new HashMap<>();
    private final Map<Integer, Subtask> subTasks = new HashMap<>();
    private final HistoryManager history = Managers.getDefaultHistory();


    @Override
    public int addTask(Task task) {                                       //Создание задачи
        task.setTaskId(++taskId);
        tasks.put(task.getId(), task);
        return taskId;
    }

    @Override
    public int addEpic(Epic epic) {                                       //Создание Эпика
        epic.setTaskId(++taskId);
        epic.setEpicId(epics.size() + 1);
        epics.put(epic.getEpicId(), epic);
        return taskId;
    }

    @Override
    public Integer addNewSubtask(Subtask subtask) {                      //Создание Подзадачи
        final int epicId = subtask.getEpicId();
        Epic epic = epics.get(epicId);
        if (epic == null) {
            return null;
        }
        final int id = subTasks.size() + 1;
        subtask.setTaskId(++taskId);
        subTasks.put(taskId, subtask);
        epic.addSubtaskId(subtask.getId());
        checkTasks(epicId);
        return id;
    }

    @Override
    public List<Map<Integer, ? extends Task>> viewTasks() {                                        //Вывод всех типов задач
        if (!tasks.isEmpty()) {
            getTasks();
        } else {
            System.out.println("Задач нет");
        }
        if (!epics.isEmpty()) {
            getEpics();
        } else {
            System.out.println("Эпиков нет");
        }
        if (!subTasks.isEmpty()) {
            getSubTasks();
        } else {
            System.out.println("Подзадач нет");
        }
        final List<Map<Integer, ? extends Task>> list = new ArrayList<>();
        list.add(tasks);
        list.add(epics);
        list.add(subTasks);
        return list;
    }

    public Map<Integer, Task> getTasks() {
        return tasks;
    }

    public Map<Integer, Epic> getEpics() {
        return epics;
    }

    public Map<Integer, Subtask> getSubTasks() {
        return subTasks;
    }

    @Override
    public void removeAllTasks() {                                     //Удаление всех задач (задачи, эпики, подхадачи)
        tasks.clear();
        epics.clear();
        subTasks.clear();
        history.clear();
    }

    @Override
    public Task getTaskById(int taskId) {                                //Получить таску по taskId
        if (tasks.containsKey(taskId)) {
            System.out.println(tasks.get(taskId));
        } else {
            System.out.println("Задачи с таким идентификатором нет!");
        }
        history.add(tasks.get(taskId));
        return tasks.get(taskId);
    }

    @Override
    public Epic getEpicById(int epicId) {                                //Получить Эпик по epicId
        if (epics.containsKey(epicId)) {
            System.out.println(epics.get(epicId));
        } else {
            System.out.println("Эпика с таким идентификатором нет!");
        }
        history.add(epics.get(epicId));
        return epics.get(epicId);
    }

    @Override
    public Subtask getSubtasksByEpic(Epic epic) {                         //Получить Подзадачи по Эпику
        if (subTasks.isEmpty()) {
            System.out.println("Подзадач нет");
            return null;
        }
        List<Integer> subtasksId = epic.getSubtasksId();
        for (Integer subtaskId : subtasksId) {
            System.out.println(subTasks.get(subtaskId));
            history.add(subTasks.get(subtaskId));
            return subTasks.get(subtaskId);
        }
        return null;
    }

    @Override
    public Subtask getSubtasksById(int id) {                             //Получение подзадачи по идентификатору
        if (subTasks.isEmpty()) {
            System.out.println("Подзадач нет");
            return null;
        }
        for (Subtask subTask : subTasks.values()) {
            if (subTask.getId() == id) {
                System.out.println(subTask);
                history.add(subTask);
                return subTask;
            }
        }
        System.out.println("Подзадачи с таким идентификатором нет!");
        return null;
    }

    @Override
    public List<Task> getHistory() {
        return history.getHistory();
    }


    @Override
    public void removeTaskById(int taskId) {                                    //Удалить задачу по taskId
        if (tasks.containsKey(taskId)) {
            history.remove(taskId);
            tasks.remove(taskId);
        } else {
            System.out.println("Задачи с таким индентификатором нет");
        }
    }

    @Override
    public void removeEpicById(int id) {                                    //Удалить Эпик по id
        if (epics.containsKey(id)) {
            Epic epic = epics.get(id);
            history.remove(id);
            removeEpic(epic);
        } else {
            System.out.println("Задачи с таким индентификатором нет");
        }
    }

    @Override
    public void removeSubtaskById(int id) {                                 //Удалить подзадачу по id
        if (!subTasks.isEmpty()) {
            for (Subtask subTask : subTasks.values()) {
                if (subTask.getId() == id) {
                    history.remove(id);
                    subTasks.remove(subTask.getId());
                    checkTasks(subTask.getEpicId());
                    return;
                }
            }
        } else {
            System.out.println("Задачи с таким индентификатором нет");
        }
    }


    @Override
    public void removeSubtask(Subtask subtask) {                  //Удалить подзадачу
        for (Subtask tempSubtask : subTasks.values()) {
            if (tempSubtask.getId() == subtask.getId()) {
                history.remove(tempSubtask.getId());
                subTasks.remove(tempSubtask.getId());
                checkTasks(tempSubtask.getEpicId());
                Epic epic = epics.get(subtask.getEpicId());
                epic.removeSubtask(subtask.getId());
                break;
            }
        }
    }

    @Override
    public void removeEpic(Epic epic) {                           //Удалить Эпик
        List<Integer> subtasksId = epic.getSubtasksId();
        for (Integer subtaskId : subtasksId) {
            history.remove(subtaskId);
            subTasks.remove(subtaskId);
        }
        epic.clearSubtaskId();
        history.remove(epic.getId());
        epics.remove(epic.getEpicId());
    }

    @Override
    public Task updateTask(Task task) {                                      //Обновить таску
        tasks.put(task.getId(), task);
        return task;
    }

    @Override
    public Epic updateEpic(Epic epic) {                                      //Обновить Эпик
        if (epics.containsKey(epic.getId())) {
            epics.put(epic.getId(), epic);
            checkTasks(epic.getId());
        }
        return epic;
    }

    @Override
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

    private void checkTasks(Integer epicId) {
        List<Status> statusTask = new ArrayList<>();
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
}
