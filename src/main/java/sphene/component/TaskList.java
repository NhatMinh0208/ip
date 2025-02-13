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

    private static Task parseSerializedTask(String taskString) throws TaskLoadFailException {
        String[] taskDescriptor = taskString.split(",");
        if (taskDescriptor.length == 0) {
            throw new TaskLoadFailException(taskString);
        }

        switch (taskDescriptor[0]) {
        case "T":
            return parseTodo(taskString, taskDescriptor);

        case "D":
            return parseDeadline(taskString, taskDescriptor);

        case "E":
            return parseEvent(taskString, taskDescriptor);

        default:
            throw new TaskLoadFailException(taskString);
        }
    }

    private static Task parseEvent(String taskString, String[] taskDescriptor) throws TaskLoadFailException {

        if (taskDescriptor.length != 5) {
            throw new TaskLoadFailException(taskString);
        }
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
    }

    private static Task parseDeadline(String taskString, String[] taskDescriptor) throws TaskLoadFailException {

        if (taskDescriptor.length != 4) {
            throw new TaskLoadFailException(taskString);
        }
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
    }

    private static Task parseTodo(String taskString, String[] taskDescriptor) throws TaskLoadFailException {
        if (taskDescriptor.length != 3) {
            throw new TaskLoadFailException(taskString);
        }
        Task t = new ToDo(taskDescriptor[2]);
        if (taskDescriptor[1].equals("1")) {
            t.markDone();
        }
        return t;
    }

    private static int compareTasksByChronologicalOrder(Task a, Task b) {
        if (a.equals(b)) {
            return 0;
        } else if (a instanceof ToDo) {
            return 1;
        } else if (b instanceof ToDo) {
            return -1;
        } else {
            LocalDateTime aCompareTime = null;
            LocalDateTime bCompareTime = null;

            if (a instanceof Deadline) {
                aCompareTime = ((Deadline) a).getDeadlineTime();
            } else if (a instanceof Event) {
                aCompareTime = ((Event) a).getStartTime();
            } else {
                assert false;
            }

            if (b instanceof Deadline) {
                bCompareTime = ((Deadline) b).getDeadlineTime();
            } else if (b instanceof Event) {
                bCompareTime = ((Event) b).getStartTime();
            } else {
                assert false;
            }

            return aCompareTime.compareTo(bCompareTime);
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
            output.append(index).append(". ").append(t.toString()).append("\n");
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

    /**
     * Sort all tasks in the list.
     */
    public void sort() {
        tasks.sort(TaskList::compareTasksByChronologicalOrder);
    }
}
