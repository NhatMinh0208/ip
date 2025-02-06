package sphene;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import sphene.command.Command;
import sphene.component.Parser;
import sphene.component.Storage;
import sphene.component.TaskList;
import sphene.component.Ui;
import sphene.exception.SpheneException;

/**
 * Main class of the Sphene bot.
 */
public class Sphene extends Application {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    private void initializeStorage(String filePath) {
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (SpheneException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Parses and executes a command from a given command string.
     * @param fullCommand The command string to process.
     */
    public void handleCommand(String fullCommand) {
        boolean isExit = false;
        try {
            Command c = Parser.parse(fullCommand);
            c.execute(tasks, ui, storage);
            isExit = c.isExit();
            if (!isExit) {
                ui.showDone(c);
            }
        } catch (SpheneException e) {
            ui.showError(e);
        } finally {
            if (isExit) {
                Platform.exit();
            }
        }
    }

    @Override
    public void start(Stage stage) {
        initializeStorage("data/tasks.txt");
        ui = new Ui(this, stage);
        ui.showWelcome();
    }
}
