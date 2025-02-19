package sphene.command;

import sphene.component.Storage;
import sphene.component.TaskList;
import sphene.component.Ui;
import sphene.exception.OutOfListRangeException;
import sphene.exception.SaveException;

/**
 * Command to mark a task on the list as not done.
 */
public class UnmarkCommand extends Command {
    private final int index;

    /**
     * Creates an unmark command.
     * @param index List index of the task to unmark.
     */
    public UnmarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "unmark " + this.index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SaveException, OutOfListRangeException {
        tasks.unmarkTask(index);
        storage.store(tasks.serialize());
        ui.print("You now have the following tasks:\n" + tasks.toString());
    }
}
