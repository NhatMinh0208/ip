package sphene.component;

import java.io.IOException;
import java.util.Scanner;

import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sphene.Sphene;
import sphene.command.Command;
import sphene.component.ui.MainWindow;
import sphene.exception.SpheneException;

/**
 * Facilities for interfacing with the user.
 */
public class Ui {
    private static final String BOT_NAME = "Sphene";

    private static final Scanner STDIN = new Scanner(System.in);

    private final Sphene sphene;
    private final Stage stage;
    private MainWindow window;

    /**
     * Creates a new Ui object.
     * @param sphene The Sphene instance that this Ui interacts with.
     * @param stage The Stage that this Ui interacts with.
     */
    public Ui(Sphene sphene, Stage stage) {
        this.sphene = sphene;
        this.stage = stage;

        initializeStage();
    }

    private void initializeStage() {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Sphene.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);

            window = fxmlLoader.getController();
            window.setUi(this); // inject this Ui instance into the window

            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles the input the user just submitted via the chatbox.
     * @param input The input from the user.
     */
    public void handleInput(String input) {
        sphene.handleCommand(input);
    }

    /**
     * Shows the welcome message (on startup).
     */
    public void showWelcome() {
        window.showSpheneDialog("Hello! I'm " + BOT_NAME + ", your gracious queen!");
        window.showSpheneDialog("How can I serve you today, my dear citizen?");
    }

    /**
     * Shows the goodbye message (on exit).
     */
    public void showGoodbye() {
        window.showSpheneDialog("I hope to serve you again, my dear citizen!");
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
        window.showSpheneDialog("I've carried out your request: " + c.toString());
    }

    /**
     * Shows a `SpheneException` to the user.
     * @param e The exception to be shown.
     */
    public void showError(SpheneException e) {
        window.showSpheneDialog("My dear citizen, I'm having trouble carrying out your request.");
        window.showSpheneDialog(e.getMessage());
    }

    /**
     * Prints a string to the user
     * @param s The string to be printed.
     */
    public void print(String s) {
        window.showSpheneDialog(s);
    }
}
