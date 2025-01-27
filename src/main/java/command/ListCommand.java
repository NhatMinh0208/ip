package command;

import component.TaskList;
import component.Ui;
import component.Storage;

public class ListCommand extends Command {
    public ListCommand() {

    }

    @Override
    public String toString() {
        return "list";
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.print(tasks.toString());
    }
}
