package sphene.component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import sphene.exception.OutOfListRangeException;
import sphene.exception.TaskLoadFailException;
import sphene.task.Deadline;
import sphene.task.ToDo;
import sphene.task.Event;
import sphene.task.Task;

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

    public TaskList(List<? extends String> taskStrings) throws TaskLoadFailException {
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

    @Override
    public String toString() {
        StringBuilder output = new StringBuilder();
        int index = 0;
        for (Task t : this.tasks) {
            index++;
            output.append(index);
            output.append(". ");
            output.append(t.toString());
            output.append("\n");
        }
        return output.toString();
    }

    public void addTask(Task t) {
        this.tasks.add(t);
    }

    public void markTask(int index) throws OutOfListRangeException {
        if (index < 1 || index > this.tasks.size()) {
            throw new OutOfListRangeException("mark", "index", index);
        }
        this.tasks.get(index-1).markDone();
    }

    public void unmarkTask(int index) throws OutOfListRangeException {
        if (index < 1 || index > this.tasks.size()) {
            throw new OutOfListRangeException("unmark", "index", index);
        }
        this.tasks.get(index-1).unmarkDone();
    }

    public Task deleteTask(int index) throws OutOfListRangeException {
        if (index < 1 || index > this.tasks.size()) {
            throw new OutOfListRangeException("delete", "index", index);
        }
        return this.tasks.remove(index-1);
    }

    /**
     * Search for tasks whose content contains a given query string.
     * @param query The query string.
     * @return A new `TaskList` containing the tasks whose content contains the query string.
     */
    public TaskList find(String query) {
        TaskList result = new TaskList();
        tasks.stream()
                .filter((task) -> task.getContent().contains(query))
                .forEach(result::addTask);
        return result;
    }
}
