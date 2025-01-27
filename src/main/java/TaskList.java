import java.util.ArrayList;
import java.util.List;

public class TaskList {
    List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<Task> tasks) {
        this.tasks = tasks;
    }

    public List<Task> getList() {
        return this.tasks;
    }

    public void addTask(Task t) {
        this.tasks.add(t);
    }

    public void markTask(int index) throws OutOfListRangeException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new OutOfListRangeException("mark", "index", index);
        }
        this.tasks.get(index).markDone();
    }

    public void unmarkTask(int index) throws OutOfListRangeException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new OutOfListRangeException("mark", "index", index);
        }
        this.tasks.get(index).unmarkDone();
    }

    public Task deleteTask(int index) throws OutOfListRangeException {
        if (index < 0 || index >= this.tasks.size()) {
            throw new OutOfListRangeException("mark", "index", index);
        }
        return this.tasks.remove(index);
    }
}
