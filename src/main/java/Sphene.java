import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Sphene {
    private static final String BOT_NAME = "Sphene";
    private static final String CMD_EXIT = "bye";
    private static final String CMD_LIST = "list";
    private static final Scanner STDIN = new Scanner(System.in);

    private static final List<Task> tasks = new ArrayList<Task>();

    public static void main(String[] args) {
        System.out.println("Hello! I'm " + BOT_NAME + ", your gracious queen!");
        System.out.println("How can I serve you today, my dear citizen?");

        // Read and evaluate command loop
        while (true) {
            String command = STDIN.nextLine();
            if (command.equals(CMD_EXIT)) {
                break;
            } else if (command.equals(CMD_LIST)) {
                int index = 0;
                System.out.println("Here are the tasks in your list, my dear citizen:");
                for (Task task : tasks) {
                    index++;
                    System.out.println(index + ". " + task);
                }
            } else {
                Task t = new Task(command);
                tasks.add(t);
                System.out.println("I've added: " + t.getContent() + " to your list!");
            }
        }

        System.out.println("I hope to serve you again soon, my dear citizen!");
    }
}