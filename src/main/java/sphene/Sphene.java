package sphene;

import sphene.command.Command;
import sphene.component.Parser;
import sphene.component.Storage;
import sphene.component.TaskList;
import sphene.component.Ui;
import sphene.exception.SpheneException;

/**
 * Main class of the Sphene bot.
 */
public class Sphene {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

    /**
     * Creates a new instance of the bot.
     * @param filePath File path to save tasks to.
     */
    public Sphene(String filePath) {
        ui = new Ui();
        storage = new Storage(filePath);
        try {
            tasks = new TaskList(storage.load());
        } catch (SpheneException e) {
            ui.showError(e);
            tasks = new TaskList();
        }
    }

    /**
     * Runs the bot.
     */
    public void run() {
        ui.showWelcome();
        boolean isExit = false;
        while (!isExit) {
            try {
                String fullCommand = ui.readCommand();
                ui.showLine(); // show the divider line ("_______")
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
                    ui.showGoodbye();
                }
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Sphene("data/tasks.txt").run();
    }
}