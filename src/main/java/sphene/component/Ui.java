package sphene.component;

import java.util.Scanner;

import sphene.command.Command;
import sphene.exception.SpheneException;

/**
 * Facilities for interfacing with the user.
 */
public class Ui {
    private static final String BOT_NAME = "Sphene";

    private static final Scanner STDIN = new Scanner(System.in);

    /**
     * Creates a new UI object.
     */
    public Ui() {

    }

    /**
     * Shows the welcome message (on startup).
     */
    public void showWelcome() {
        System.out.println("Hello! I'm " + BOT_NAME + ", your gracious queen!");
        System.out.println("How can I serve you today, my dear citizen?");
    }

    /**
     * Shows the goodbye message (on exit).
     */
    public void showGoodbye() {
        System.out.println("I hope to serve you again, my dear citizen!");
    }

    /**
     * Shows a line divider.
     */
    public void showLine() {
        System.out.println("__________________________________");
    }

    /**
     * Alerts the user that a command has been executed successfully.
     * @param c The executed command.
     */
    public void showDone(Command c) {
        System.out.println("I've carried out your request: " + c.toString());
    }

    /**
     * Reads a command line from the user.
     * @return The read command string.
     */
    public String readCommand() {
        return STDIN.nextLine();
    }

    /**
     * Shows a `SpheneException` to the user.
     * @param e The exception to be shown.
     */
    public void showError(SpheneException e) {
        System.out.println(e.getMessage());
    }

    /**
     * Prints a string to the user
     * @param s The string to be printed.
     */
    public void print(String s) {
        System.out.print(s);
    }

    /**
     * Prints a string to the user, followed by a newline.
     * @param s The string to be printed.
     */
    public void println(String s) {
        System.out.println(s);
    }
}
