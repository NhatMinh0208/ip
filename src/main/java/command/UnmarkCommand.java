package command;

import component.Storage;
import component.TaskList;
import component.Ui;
import exception.OutOfListRangeException;
import exception.SaveException;

public class UnmarkCommand extends Command {
    private final int index;

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
    }
}