import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

public class TaskList {

    private static Task parseSerializedTask(String taskString) throws TaskLoadFailException {
        String[] taskDescriptor = taskString.split(",");
        if (taskDescriptor.length == 0) {
            throw new TaskLoadFailException(taskString);
        } else {
            switch (taskDescriptor[0]) {
            case "T":
                if (taskDescriptor.length == 3) {
                    Task t = new ToDo(taskDescriptor[2]);
                    if (taskDescriptor[1].equals("1")) {
                        t.markDone();
                    }
                    return t;
                } else {
                    throw new TaskLoadFailException(taskString);
                }
            case "D":
                if (taskDescriptor.length == 4) {
                    try {
                        Task t = new Deadline(taskDescriptor[2],
                                LocalDateTime.parse(taskDescriptor[3], DateTimeFormatter.ISO_DATE_TIME));
                        if (taskDescriptor[1].equals("1")) {
                            t.markDone();
                        }
                        return t;
                    } catch (DateTimeParseException e) {
                        throw new TaskLoadFailException(taskString);
                    }
                } else {
                    throw new TaskLoadFailException(taskString);
                }
            case "E":
                if (taskDescriptor.length == 5) {
                    try {
                        Task t = new Event(taskDescriptor[2],
                                LocalDateTime.parse(taskDescriptor[3], DateTimeFormatter.ISO_DATE_TIME),
                                LocalDateTime.parse(taskDescriptor[4], DateTimeFormatter.ISO_DATE_TIME));
                        if (taskDescriptor[1].equals("1")) {
                            t.markDone();
                        }
                        return t;
                    } catch (DateTimeParseException e) {
                        throw new TaskLoadFailException(taskString);
                    }
                } else {
                    throw new TaskLoadFailException(taskString);
                }
            default:
                throw new TaskLoadFailException(taskString);
            }
        }

    }

    List<Task> tasks;

    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    public TaskList(List<String> taskStrings) throws TaskLoadFailException {
        this.tasks = new ArrayList<Task>();
        for (String s : taskStrings) {
            this.tasks.add(parseSerializedTask(s));
        }
    }

    public List<String> serialize() {
        List<String> strings = new ArrayList<>();
        for (Task t : this.tasks) {
            strings.add(t.serialize());
        }
        return strings;
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
