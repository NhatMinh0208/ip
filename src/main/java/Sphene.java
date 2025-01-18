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

    public static void main(String[] args) {
        System.out.println("Hello! I'm " + BOT_NAME + ", your gracious queen!");
        System.out.println("How can I serve you today, my dear citizen?");

        // Read and evaluate command loop
        loop:
        while (true) {
            String command = STDIN.next();
            switch (command) {
                case CMD_EXIT:
                    break loop;
                case CMD_LIST: {
                    listTasks();
                    break;
                }
                case CMD_TODO: {
                    try {
                        String params = STDIN.findInLine(PATTERN_TODO);
                        Matcher m = PATTERN_TODO.matcher(params);
                        m.matches();
                        addToDo(m.group(1));
                    } catch (Exception e) {
                        System.out.println("ERROR: Could not parse todo line " + e.getClass());
                    }
                    break;
                }
                case CMD_DEADLINE: {
                    try {
                        String params = STDIN.findInLine(PATTERN_DEADLINE);
                        Matcher m = PATTERN_DEADLINE.matcher(params);
                        m.matches();
                        addDeadline(m.group(1), m.group(2));
                    } catch (Exception e) {
                        System.out.println("ERROR: Could not parse deadline line " + e.getClass());
                    }
                    break;
                }
                case CMD_EVENT: {
                    try {
                        String params = STDIN.findInLine(PATTERN_EVENT);
                        Matcher m = PATTERN_EVENT.matcher(params);
                        m.matches();
                        addEvent(m.group(1), m.group(2), m.group(3));
                    } catch (Exception e) {
                        System.out.println("ERROR: Could not parse event line " + e);
                    }
                    break;
                }
                case CMD_MARK: {
                    int index = STDIN.nextInt();
                    markTask(index);
                    break;
                }
                case CMD_UNMARK: {
                    int index = STDIN.nextInt();
                    unmarkTask(index);
                    break;
                }
                default:
                    System.out.println("My dear citizen, I'm sorry, but I don't understand what you want me to do.");
                    break;
            }
        }

        System.out.println("I hope to serve you again soon, my dear citizen!");
    }
}