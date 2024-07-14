package ru.practicum.java.manager;

import ru.practicum.java.Exception.ManagerSaveException;
import ru.practicum.java.tasks.Epic;
import ru.practicum.java.tasks.Status;
import ru.practicum.java.tasks.Subtask;
import ru.practicum.java.tasks.Task;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class FileBackedTaskManager extends InMemoryTaskManager {

    public Path file;
    private Path path = Paths.get("C:\\java-kanban\\count.txt");
    public File counter = path.toFile();
    List<Integer> listId = new ArrayList();

    public FileBackedTaskManager(File file) {
        this.file = file.toPath();
        counter = path.toFile();
    }

    public static FileBackedTaskManager loadFromFile(File file) throws IOException {
        if (file.exists()) {
            FileBackedTaskManager fileBackedTaskManager = new FileBackedTaskManager(file);
            List<String> list = Files.readAllLines(file.toPath());
            list.removeFirst();
            String nameTask;
            String description;
            Status status;
            int id;
            int epicId;
            for (String line : list) {
                String[] parts = line.split(",");
                if (parts[1].equals("TASK")) {
                    nameTask = parts[2];
                    description = parts[4];
                    status = Status.valueOf(parts[3]);
                    id = Integer.parseInt(parts[0]);
                    Task task = new Task(nameTask, description);
                    task.setStatus(status);
                    task.setTaskId(id);
                    fileBackedTaskManager.addTask(task);
                } else if (parts[1].equals("EPIC")) {
                    nameTask = parts[2];
                    description = parts[4];
                    status = Status.valueOf(parts[3]);
                    id = Integer.parseInt(parts[0]);
                    Epic epic = new Epic(nameTask, description);
                    epic.setStatus(status);
                    epic.setTaskId(id);
                    fileBackedTaskManager.addEpic(epic);
                } else if (parts[1].equals("SUBTASK")) {
                    nameTask = parts[2];
                    description = parts[4];
                    epicId = Integer.parseInt(parts[5]);
                    status = Status.valueOf(parts[3]);
                    id = Integer.parseInt(parts[0]);
                    Subtask subtask = new Subtask(nameTask, description, epicId);
                    subtask.setStatus(status);
                    subtask.setTaskId(id);
                    fileBackedTaskManager.addNewSubtask(subtask);
                }
            }
            return fileBackedTaskManager;
        } else {
            System.out.println("File does not exist");
        }
        return null;
    }

    @Override
    public int addTask(Task task) {
        super.addTask(task);
        save();
        return task.getId();
    }

    @Override
    public int addEpic(Epic epic) {
        super.addEpic(epic);
        save();
        return epic.getId();
    }

    @Override
    public Integer addNewSubtask(Subtask subtask) {
        super.addNewSubtask(subtask);
        save();
        return subtask.getId();
    }

    @Override
    public void removeTaskById(int taskId) {
        super.removeTaskById(taskId);
        save();
    }

    @Override
    public void removeEpicById(int id) {
        super.removeEpicById(id);
        save();
    }

    @Override
    public void removeSubtaskById(int id) {
        super.removeSubtaskById(id);
        save();
    }

    @Override
    public void removeSubtask(Subtask subtask) {
        super.removeSubtask(subtask);
        save();
    }

    @Override
    public void removeEpic(Epic epic) {
        super.removeEpic(epic);
        save();
    }

    @Override
    public Task updateTask(Task task) {
        super.updateTask(task);
        save();
        return task;
    }

    @Override
    public Epic updateEpic(Epic epic) {
        super.updateEpic(epic);
        save();
        return epic;
    }

    @Override
    public Subtask updateSubTask(Subtask subtask) {
        super.updateSubTask(subtask);
        save();
        return subtask;
    }

    private void save() {
        if (file.toFile().length() == 0) {
            try {
                try (FileWriter fw = new FileWriter(file.toFile(), StandardCharsets.UTF_8, true)) {
                    fw.write("id,type,name,status,description,epic\n");
                } catch (IOException e) {
                    throw new ManagerSaveException("Error save task in File");
                }
            } catch (ManagerSaveException e) {
                e.printStackTrace();
            }
        }
        if (!counter.exists()) {
            try {
                counter = Files.createFile(path).toFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        Map<Integer, Task> tasks = super.getTasks();
        Map<Integer, Epic> epics = super.getEpics();
        Map<Integer, Subtask> subtasks = super.getSubTasks();
        saveTask(tasks);
        saveEpic(epics);
        saveSubTask(subtasks);
    }

    private void saveTask(Map<Integer, Task> tasks) {
        for (Task task : tasks.values()) {
            String taskInString = toString(task);
            String[] lines = taskInString.split(",");
            boolean isExist = checkExist(lines);
            if (!isExist) {
                int id = task.getId();
                saveInFile(taskInString, lines, id);
            }
        }
    }

    private void saveEpic(Map<Integer, Epic> epics) {
        for (Epic epic : epics.values()) {
            String taskInString = toString(epic);
            String[] lines = taskInString.split(",");
            boolean isExist = checkExist(lines);
            if (!isExist) {
                int id = epic.getId();
                saveInFile(taskInString, lines, id);
            }
        }
    }

    private void saveSubTask(Map<Integer, Subtask> subtasks) {
        for (Subtask subtask : subtasks.values()) {
            String taskInString = toString(subtask);
            String[] lines = taskInString.split(",");
            boolean isExist = checkExist(lines);
            if (!isExist) {
                int id = subtask.getId();
                saveInFile(taskInString, lines, id);
            }
        }
    }

    private boolean checkExist(String[] lines) {
        boolean isExist = false;
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(counter, StandardCharsets.UTF_8))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                if (line.equals(lines[0])) {
                    isExist = true;
                    break;
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return isExist;
    }

    private void saveInFile(String taskInString, String[] lines, int id) {
        try (FileWriter fw = new FileWriter(file.toFile(), StandardCharsets.UTF_8, true); FileWriter count = new FileWriter(path.toFile(), StandardCharsets.UTF_8, true)) {
            fw.write(taskInString);
            listId.add(Integer.valueOf(lines[0]));
            count.write(id + "\n");
        } catch (IOException e) {
            System.out.println("Error saving file");
        }
    }

    private String toString(Task task) {
        String taskInString = task.toString();
        int id = task.getId();
        Status status = task.getStatus();
        StringBuilder taskBuilder = new StringBuilder(taskInString);
        int start = taskBuilder.indexOf("'");
        int end = taskBuilder.indexOf("'", start + 1);
        String name = taskBuilder.substring(start + 1, end);
        start = taskBuilder.indexOf("'", end + 1);
        end = taskBuilder.indexOf("'", start + 1);
        String description = taskBuilder.substring(start + 1, end);
        int epic = 0;
        TypeTask type = null;
        if (task.getClass() == Subtask.class) {
            type = TypeTask.SUBTASK;
            Subtask sub = (Subtask) task;
            epic = sub.getEpicId();
            return String.format("%d,%s,%s,%s,%s,%s\n", id, type, name, status, description, epic);
        } else if (task.getClass() == Epic.class) {
            type = TypeTask.EPIC;
            return String.format("%d,%s,%s,%s,%s\n", id, type, name, status, description);
        } else if (task.getClass() == Task.class) {
            type = TypeTask.TASK;
            return String.format("%d,%s,%s,%s,%s\n", id, type, name, status, description);
        }
        return null;
    }
}
