package sphene.command;

import sphene.component.Storage;
import sphene.component.TaskList;
import sphene.component.Ui;

/**
 * Command to sort the task list by deadline/start time in chronological order.
 */
public class SortCommand extends Command {
    /**
     * Creates a new sort command.
     */
    public SortCommand() {

    }

    @Override
    public String toString() {
        return "sort";
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.print(tasks.toString());
    }
}
