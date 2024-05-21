import java.util.List;
import java.util.Map;

public class Subtask extends Task {

    public Subtask(Object nameTask, Object description) {
        super(nameTask, description);
    }


    @Override
    public void setStatus(Enum Status) {
        super.setStatus(Status);
        TaskManager.checkTask(this);
    }


    //Получить Эпик по подзадаче
    public static Epic getEpic(Subtask subtask, Map<Epic, List<Subtask>> epicsAndSubTasks) {
        Epic newEpic = null;
        for (Epic epic : epicsAndSubTasks.keySet()) {
            for (Subtask subTask : epicsAndSubTasks.get(epic)) {
                if (subTask.equals(subtask)) {
                    newEpic = epic;
                }
            }
        }
        return newEpic;
    }


    @Override
    public String toString() {
        return " Подзадача " + uuid + " {" +
                " nameTask='" + nameTask + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status +
                '}';
    }
}
