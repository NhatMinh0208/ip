package sphene.command;

import sphene.component.Storage;
import sphene.component.TaskList;
import sphene.component.Ui;
import sphene.exception.SaveException;

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
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SaveException {
        tasks.sort();
        ui.print(tasks.toString());
        storage.store(tasks.serialize());
    }
}
