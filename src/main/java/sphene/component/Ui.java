package sphene.component;

import java.util.Scanner;

import sphene.command.Command;
import sphene.exception.SpheneException;

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

    public void showDone(Command c) {
        System.out.println("I've carried out your request: " + c.toString());
    }

    public String readCommand() {
        return STDIN.nextLine();
    }

    public void showError(SpheneException e) {
        System.out.println(e.getMessage());
    }

    public void print(String s) {
        System.out.print(s);
    }

    public void println(String s) {
        System.out.println(s);
    }
}
