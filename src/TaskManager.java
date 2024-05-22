import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TaskManager {

    private static int uuid = 0;

    public static int epicId = 0;
    public final Map<Integer, Task> tasks = new HashMap<>();
    public final static Map<Integer, Epic> epics = new HashMap<>();
    public final static Map<Epic, List<Subtask>> epicsAndSubTasks = new HashMap<>();
    private List<Subtask> subTasks = new ArrayList<>();

    private static List<Status> statusTask = new ArrayList<>();


    public static int generateId() {                                 //Генерация уникального id для каждой задачи
        return ++uuid;
    }

    public static int generateEpicId() {                                 //Генерация уникального id для каждой задачи
        return ++epicId;
    }

    public void addTask(Task task) {                                       //Создание задачи
        tasks.put(task.uuid, task);
    }

    public void addEpic(Epic epic) {                                       //Создание Эпика
        epics.put(epic.uuid, epic);
        epicsAndSubTasks.put(epic, null);
    }

    public void addSubTasks(Subtask subtask) {                   //Создание Подзадачи для Эпика
        Epic epic = Subtask.getEpic(subtask, epics);
        if (!epicsAndSubTasks.isEmpty() && epicsAndSubTasks.containsKey(epic) && epicsAndSubTasks.get(epic) != null) {
            subTasks = epicsAndSubTasks.get(epic);
            subTasks.add(subtask);
        } else {
            subTasks = new ArrayList<>();
            subTasks.add(subtask);
            epicsAndSubTasks.put(epic, subTasks);
        }
        checkTasks(epic, epicsAndSubTasks);
    }

    public static void checkTasks(Epic epic, Map<Epic, List<Subtask>> epicsAndSubTasks) {
        int countStatusDONE = 0;
        int countStatusNEW = 0;
        if (!epicsAndSubTasks.isEmpty()) {
            for (Subtask subTask : epicsAndSubTasks.get(epic)) {
                statusTask.add(subTask.status);
            }
            if (statusTask.isEmpty()) {
                epic.status = Status.NEW;
                return;
            }
            for (Status status : statusTask) {
                if (status == Status.IN_PROGRESS) {
                    epic.status = Status.IN_PROGRESS;
                    statusTask.clear();
                    return;
                } else if (status == Status.DONE) {
                    countStatusDONE++;
                } else if (status == Status.NEW) {
                    countStatusNEW++;
                }
            }
            if (countStatusDONE == statusTask.size()) {
                epic.status = Status.DONE;
                statusTask.clear();
            } else if (countStatusNEW == statusTask.size()) {
                epic.status = Status.NEW;
                statusTask.clear();
            } else {
                epic.status = Status.IN_PROGRESS;
                statusTask.clear();
            }
        } else {
            epic.status = Status.NEW;
        }
    }

    public static void checkTask(Subtask subtask) {                         //Проверить статус таски
        Epic epic = Subtask.getEpic(subtask, epics);
        checkTasks(epic, epicsAndSubTasks);
    }

    public void viewTasks() {                                                 //Вывод всех типов задач
        if (!tasks.isEmpty()) {
            System.out.println(tasks);
        } else {
            System.out.println("Задач нет");
        }
        if (!epics.isEmpty()) {
            if (epicsAndSubTasks.isEmpty()) {
                System.out.println(epics);
            } else {
                System.out.println(epicsAndSubTasks);
            }
        } else {
            System.out.println("Эпиков нет");
        }
    }

    public void removeAllTasks() {                                     //Удаление всех задач (задачи, эпики, подхадачи)
        tasks.clear();
        epics.clear();
        epicsAndSubTasks.clear();
        subTasks.clear();
    }

    public void getTaskById(int uuid) {                                //Получить таску по uuid
        if (tasks.containsKey(uuid)) {
            System.out.println(tasks.get(uuid));
        } else {
            System.out.println("Задачи с таким идентификатором нет!");
        }
    }

    public void getEpicById(int uuid) {                                //Получить Эпик по uuid
        if (epics.containsKey(uuid)) {
            System.out.println(epics.get(uuid));
        } else {
            System.out.println("Эпика с таким идентификатором нет!");
        }
    }

    public void getSubtasksByEpic(Epic epic) {                         //Получить Подзадачу по Эпику
        if (epicsAndSubTasks.isEmpty()) {
            System.out.println("Подзадач нет");
            return;
        }
        if (epicsAndSubTasks.get(epic) != null) {
            System.out.println(epicsAndSubTasks.get(epic));
        } else {
            System.out.println("Подзадач у эпика нет!");
        }
    }

    public void getSubtasksById(int uuid) {                             //Получение подзадачи по идентификатору
        if (epicsAndSubTasks.isEmpty()) {
            System.out.println("Подзадач нет");
            return;
        }
        for (Epic epic : epicsAndSubTasks.keySet()) {
            for (Subtask subTask : epicsAndSubTasks.get(epic)) {
                if (subTask.uuid == uuid) {
                    System.out.println(subTask);
                    return;
                }
            }
        }
        System.out.println("Подзадачи с таким идентификатором нет!");
    }

    public void removeById(int uuid) {                                    //Удалить по uuid
        if (tasks.containsKey(uuid)) {
            System.out.println(tasks.remove(uuid));
        } else if (epics.containsKey(uuid)) {
            System.out.println(epics.remove(uuid));
        } else if (!epicsAndSubTasks.isEmpty()) {
            for (Epic epic : epicsAndSubTasks.keySet()) {
                for (Subtask subTask : epicsAndSubTasks.get(epic)) {
                    if (subTask.uuid == uuid) {
                        removeSubtask(subTask);
                        checkTasks(epic, epicsAndSubTasks);
                        return;
                    }
                }
            }
        }
        System.out.println("Идентификатор не найден");
    }

    private void removeSubtask(Subtask subtask) {                            //Удалить подзадачу (Только для менеджера)
        subTasks.remove(subtask);
    }

    public void removeSubtask(Epic epic, Subtask subtask) {                  //Удалить подзадачу
        subTasks.remove(subtask);
        checkTasks(epic, epicsAndSubTasks);
    }

    public void removeEpic(Epic epic) {                           //Удалить Эпик
        epics.remove(epic.epicId);
        epicsAndSubTasks.remove(epic);
    }

    public void updateTask(Task task) {                                      //Обновить таску
        tasks.put(task.getId(), task);
    }

    public void updateEpic(Epic epic) {                                      //Обновить Эпик
        epics.put(epic.getId(), epic);
    }

    public void updateSubTask(Subtask subtask) {                             //Обновить Подзадачу
        Epic epic = Subtask.getEpic(subtask, epics);
        if (epic != null) {
            subTasks = epicsAndSubTasks.get(epic);
            subTasks.add(subtask);
        } else {
            System.out.println("Эпик не найден!");
        }
    }
}
