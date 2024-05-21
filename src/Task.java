

public class Task {

    protected final Object nameTask;
    protected final Object description;
    protected final int uuid;
    protected Status status;


    public Task(Object nameTask, Object description) {
        this.nameTask = nameTask;
        this.description = description;
        this.uuid = TaskManager.generateId();
        this.status = Status.NEW;
    }


    public void setStatus(Enum Status) {
            status = (Status) Status;
    }


    public int getId() {
        return uuid;
    }


    @Override
    public String toString() {
        return " Задача " + uuid + " { " +
                "nameTask='" + nameTask + '\'' +
                ", description='" + description + '\'' +
                ", status=" + status;
    }
}
