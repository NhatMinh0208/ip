package sphene.command;

import sphene.exception.OutOfListRangeException;
import sphene.exception.SaveException;
import sphene.component.TaskList;
import sphene.component.Ui;
import sphene.component.Storage;

/**
 * Command for deleting a task on the list.
 */
public class DeleteCommand extends Command {
    private final int index;

    /**
     * Creates a new delete command.
     * @param index List index of the task to be deleted.
     */
    public DeleteCommand(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "delete " + this.index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SaveException, OutOfListRangeException {
        tasks.deleteTask(index);
        storage.store(tasks.serialize());
    }
}