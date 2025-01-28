package sphene.command;

import sphene.component.TaskList;
import sphene.component.Ui;
import sphene.component.Storage;

/**
 * Command to print all tasks in the task list.
 */
public class ListCommand extends Command {
    /**
     * Creates a new list command.
     */
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
