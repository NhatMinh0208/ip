package sphene.command;

import sphene.exception.OutOfListRangeException;
import sphene.exception.SaveException;
import sphene.component.TaskList;
import sphene.component.Ui;
import sphene.component.Storage;

/**
 * Generic command.
 */
public abstract class Command {
    /**
     * Creates a new generic command.
     */
    public Command() {

    }

    @Override
    public String toString() {
        return "[sphene.command]";
    }

    /**
     * Checks if the command is an exit command.
     * @return `True` if command is an exit command, `False` otherwise.
     */
    public boolean isExit() {
        return false;
    }

    /**
     * Executes the command on the given `TaskList`, `Ui` and `Storage`.
     * @param tasks `TaskList` to be used.
     * @param ui `Ui` to be used.
     * @param storage `Storage` to be used.
     * @throws SaveException If TaskList cannot be saved to Storage.
     * @throws OutOfListRangeException If an index is outside the range of valid list indices.
     */
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SaveException, OutOfListRangeException {

    }
}
