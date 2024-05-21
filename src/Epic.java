import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Epic extends Task {

    public static List<Status> statusTask = new ArrayList<>();

    public Epic(Object nameTask, Object description) {
        super(nameTask, description);
    }


    @Override
    public void setStatus(Enum newStatus) {
        super.setStatus(newStatus);
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


    @Override
    public String toString() {
        return " Эпик { " +
                "nameTask='" + nameTask + '\'' +
                ", description=" + description +
                ", status=" + status +
                '}';
    }
}
