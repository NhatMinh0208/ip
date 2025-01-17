import java.util.Scanner;

public class Sphene {
    private static final String BOT_NAME = "Sphene";
    private static final String CMD_EXIT = "bye";
    private static final Scanner STDIN = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Hello! I'm " + BOT_NAME + "!");
        System.out.println("How can I serve you today, my precious citizen?");

        // Read and evaluate command loop
        while (true) {
            String command = STDIN.nextLine();
            if (command.equals(CMD_EXIT)) {
                break;
            } else {
                System.out.println(command);
            }
        }

        System.out.println("I hope to serve you again soon!");
    }
}