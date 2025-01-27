package command;

import component.Storage;
import component.TaskList;
import component.Ui;
import exception.OutOfListRangeException;
import exception.SaveException;

public class MarkCommand extends Command {
    private final int index;

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
