package sphene.component;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;

import sphene.exception.OutOfListRangeException;
import sphene.exception.TaskLoadFailException;
import sphene.task.Deadline;
import sphene.task.Event;
import sphene.task.Task;
import sphene.task.ToDo;

/**
 * Abstraction for a list of tasks.
 */
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

    private final List<Task> tasks;

    /**
     * Creates a new empty task list.
     */
    public TaskList() {
        this.tasks = new ArrayList<>();
    }

    /**
     * Parses the given serialized task strings into a task list.
     * @param taskStrings The serialized task strings to be parsed.
     * @throws TaskLoadFailException If one or more serialized strings cannot be parsed.
     */
    public TaskList(List<String> taskStrings) throws TaskLoadFailException {
        this.tasks = new ArrayList<Task>();
        for (String s : taskStrings) {
            this.tasks.add(parseSerializedTask(s));
        }
    }

    /**
     * Serializes the task list.
     * @return The serialized task strings from the list.
     */
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

    /**
     * Adds a task to the list.
     * @param t Task to be added.
     */
    public void addTask(Task t) {
        this.tasks.add(t);
    }

    /**
     * Marks a task on the list as done.
     * @param index Index of the task to be marked.
     * @throws OutOfListRangeException If `index` is outside the range of valid list indices.
     */
    public void markTask(int index) throws OutOfListRangeException {
        if (index < 1 || index > this.tasks.size()) {
            throw new OutOfListRangeException("mark", "index", index);
        }
        this.tasks.get(index - 1).markDone();
    }

    /**
     * Marks a task on the list as not done.
     * @param index Index of the task to be unmarked.
     * @throws OutOfListRangeException If `index` is outside the range of valid list indices.
     */
    public void unmarkTask(int index) throws OutOfListRangeException {
        if (index < 1 || index > this.tasks.size()) {
            throw new OutOfListRangeException("unmark", "index", index);
        }
        this.tasks.get(index - 1).unmarkDone();
    }

    /**
     * Deletes a task on the list.
     * @param index Index of the task to be marked.
     * @throws OutOfListRangeException If `index` is outside the range of valid list indices.
     */
    public Task deleteTask(int index) throws OutOfListRangeException {
        if (index < 1 || index > this.tasks.size()) {
            throw new OutOfListRangeException("delete", "index", index);
        }
        return this.tasks.remove(index - 1);
    }
}
