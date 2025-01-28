package sphene.command;

import sphene.component.TaskList;
import sphene.component.Ui;
import sphene.component.Storage;

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
