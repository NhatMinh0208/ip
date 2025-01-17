import java.util.Scanner;

public class Sphene {
    private static final String CMD_EXIT = "bye";
    public static void main(String[] args) {
        String name = "Sphene";

        System.out.println("Hello! I'm " + name + "!");
        System.out.println("How can I serve you today, my precious citizen?");

        // Read and evaluate command loop
        while (true) {
            Scanner stdin = new Scanner(System.in);
            String command = stdin.nextLine();
            if (command.equals(CMD_EXIT)) {
                break;
            } else {
                System.out.println(command);
            }
        }

        System.out.println("I hope to serve you again soon!");
    }
}