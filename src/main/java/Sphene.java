import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sphene {
    private static final String BOT_NAME = "Sphene";
    private static final String CMD_EXIT = "bye";
    private static final String CMD_LIST = "list";
    private static final String CMD_MARK = "mark";
    private static final String CMD_UNMARK = "unmark";
    private static final Scanner STDIN = new Scanner(System.in);

    private static final List<Task> tasks = new ArrayList<>();

    private static void addTask(String content) {
        Task t = new Task(content);
        tasks.add(t);
        System.out.println("I've added task: " + t.getContent() + " to your list!");
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
                    addTask(command);
                    break;
            }
        }

        System.out.println("I hope to serve you again soon, my dear citizen!");
    }
}