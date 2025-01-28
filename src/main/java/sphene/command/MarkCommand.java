package sphene.command;

import sphene.component.Storage;
import sphene.component.TaskList;
import sphene.component.Ui;
import sphene.exception.OutOfListRangeException;
import sphene.exception.SaveException;

/**
 * Command to mark a task on the list as done.
 */
public class MarkCommand extends Command {
    private final int index;

    /**
     * Creates a new mark command.
     * @param index List index of the task to be marked as done.
     */
    public MarkCommand(int index) {
        this.index = index;
    }

    @Override
    public String toString() {
        return "mark " + this.index;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SaveException, OutOfListRangeException {
        tasks.markTask(index);
        storage.store(tasks.serialize());
    }
}
