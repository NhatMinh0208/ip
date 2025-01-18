import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Sphene {
    private static final String BOT_NAME = "Sphene";

    private static final String CMD_EXIT = "bye";
    private static final String CMD_LIST = "list";
    private static final String CMD_MARK = "mark";
    private static final String CMD_UNMARK = "unmark";
    private static final String CMD_TODO = "todo";
    private static final String CMD_DEADLINE = "deadline";
    private static final String CMD_EVENT = "event";

    private static final Pattern PATTERN_TODO = Pattern.compile(" *(.*)");
    private static final Pattern PATTERN_DEADLINE = Pattern.compile(" *(.*)/by *(.*)");
    private static final Pattern PATTERN_EVENT = Pattern.compile(" *(.*)/from *(.*)/to *(.*)");
    private static final Pattern PATTERN_MARK = Pattern.compile(" *([0-9]+) *");
    private static final Pattern PATTERN_UNMARK = PATTERN_MARK;

    private static final Scanner STDIN = new Scanner(System.in);

    private static final List<Task> tasks = new ArrayList<>();

    private static void notifyAddTask(String type, String content) {
        System.out.println("I've added " + type + ": " + content + " to your list!");
    }

    private static void addToDo(String content) {
        Task t = new ToDo(content);
        tasks.add(t);
        notifyAddTask("todo", t.getContent());
    }

    private static void addDeadline(String content, String by) {
        Task t = new Deadline(content, by);
        tasks.add(t);
        notifyAddTask("deadline", t.getContent());
    }

    private static void addEvent(String content, String from, String to) {
        Task t = new Event(content, from, to);
        tasks.add(t);
        notifyAddTask("event", t.getContent());
    }

    private static void listTasks() {
        int index = 0;
        System.out.println("Here are the tasks in your list, my dear citizen:");
        for (Task task : tasks) {
            index++;
            System.out.println(index + ". " + task);
        }
    }

    private static void markTask(int index) {
        Task t = tasks.get(index-1);
        t.markDone();
        System.out.println("I've marked task: " + t.getContent() + " as done!");
    }

    private static void unmarkTask(int index) {
        Task t = tasks.get(index-1);
        t.unmarkDone();
        System.out.println("I've marked task: " + t.getContent() + " as not done.");
    }

    private static void parseToDo() throws SyntaxException, EmptyFieldException {
        String params = STDIN.nextLine();
        Matcher m = PATTERN_TODO.matcher(params);
        if (m.matches()) {
            if (m.group(1).isEmpty()) {
                throw new EmptyFieldException(CMD_TODO, params, "content");
            }
            addToDo(m.group(1));
        } else {
            throw new SyntaxException(CMD_TODO, params);
        }
    }

    private static void parseDeadline() throws SyntaxException, EmptyFieldException {
        String params = STDIN.nextLine();
        Matcher m = PATTERN_DEADLINE.matcher(params);
        if (m.matches()) {
            if (m.group(1).isEmpty()) {
                throw new EmptyFieldException(CMD_DEADLINE, params, "content");
            }
            if (m.group(2).isEmpty()) {
                throw new EmptyFieldException(CMD_DEADLINE, params, "by");
            }
            addDeadline(m.group(1), m.group(2));
        } else {
            throw new SyntaxException(CMD_DEADLINE, params);
        }
    }

    private static void parseEvent() throws SyntaxException, EmptyFieldException {
        String params = STDIN.nextLine();
        Matcher m = PATTERN_EVENT.matcher(params);
        if (m.matches()) {
            if (m.group(1).isEmpty()) {
                throw new EmptyFieldException(CMD_EVENT, params, "content");
            }
            if (m.group(2).isEmpty()) {
                throw new EmptyFieldException(CMD_EVENT, params, "from");
            }
            if (m.group(3).isEmpty()) {
                throw new EmptyFieldException(CMD_EVENT, params, "to");
            }
            addEvent(m.group(1), m.group(2), m.group(3));
        } else {
            throw new SyntaxException(CMD_EVENT, params);
        }
    }

    private static void parseMark() throws SyntaxException, OutOfListRangeException {
        String params = STDIN.nextLine();
        Matcher m = PATTERN_MARK.matcher(params);
        if (m.matches()) {
            int index = Integer.parseInt(m.group(1));
            if (index < 1 || index > tasks.size()) {
                throw new OutOfListRangeException(CMD_MARK, params, "index", index);
            }
            markTask(index);
        } else {
            throw new SyntaxException(CMD_MARK, params);
        }
    }

    private static void parseUnmark() throws SyntaxException, OutOfListRangeException {
        String params = STDIN.nextLine();
        Matcher m = PATTERN_UNMARK.matcher(params);
        if (m.matches()) {
            int index = Integer.parseInt(m.group(1));
            if (index < 1 || index > tasks.size()) {
                throw new OutOfListRangeException(CMD_UNMARK, params, "index", index);
            }
            unmarkTask(index);
        } else {
            throw new SyntaxException(CMD_UNMARK, params);
        }
    }

    public static void main(String[] args) {
        System.out.println("Hello! I'm " + BOT_NAME + ", your gracious queen!");
        System.out.println("How can I serve you today, my dear citizen?");

        // Read and evaluate command loop
        loop:
        while (true) {
            try {
                String command = STDIN.next();
                switch (command) {
                    case CMD_EXIT:
                        break loop;
                    case CMD_LIST: {
                        String params = STDIN.nextLine();
                        listTasks();
                        break;
                    }
                    case CMD_TODO: {
                        parseToDo();
                        break;
                    }
                    case CMD_DEADLINE: {
                        parseDeadline();
                        break;
                    }
                    case CMD_EVENT: {
                        parseEvent();
                        break;
                    }
                    case CMD_MARK: {
                        parseMark();
                        break;
                    }
                    case CMD_UNMARK: {
                        parseUnmark();
                        break;
                    }
                    default:
                        throw new UnknownCommandException(command);
                }
            } catch (SpheneException e) {
                System.out.println("My dear citizen, I'm having trouble completing your request:");
                System.out.println(e.dialogue());
            }
        }
        System.out.println("I hope to serve you again soon, my dear citizen!");
    }
}