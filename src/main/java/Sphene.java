import command.Command;
import component.Parser;
import component.Storage;
import component.Ui;
import exception.SpheneException;
import component.TaskList;

public class Sphene {
    private Storage storage;
    private TaskList tasks;
    private Ui ui;

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
            } catch (SpheneException e) {
                ui.showError(e);
            } finally {
                ui.showLine();
            }
        }
    }

    public static void main(String[] args) {
        new Sphene("data/tasks.txt").run();
    }
}