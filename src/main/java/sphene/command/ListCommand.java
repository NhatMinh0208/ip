package sphene.command;

import sphene.component.Storage;
import sphene.component.TaskList;
import sphene.component.Ui;

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
        ui.print("You have the following tasks:\n" + tasks.toString());
    }
}
