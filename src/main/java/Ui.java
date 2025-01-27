import java.util.Scanner;
import java.util.regex.Pattern;

public class Ui {
    private static final String BOT_NAME = "Sphene";

    private final Scanner STDIN = new Scanner(System.in);

    public Ui() {

    }

    public void showWelcome() {
        System.out.println("Hello! I'm " + BOT_NAME + ", your gracious queen!");
        System.out.println("How can I serve you today, my dear citizen?");
    }

    public void showLine() {
        System.out.println("__________________________________");
    }

    public String readCommand() {
        return STDIN.nextLine();
    }

    public void showError(SpheneException e) {
        System.out.println(e.getMessage());
    }
}
