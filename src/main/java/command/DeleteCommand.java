package command;

import exception.OutOfListRangeException;
import exception.SaveException;
import component.TaskList;
import component.Ui;
import component.Storage;

public class DeleteCommand extends Command {
    private final int index;

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