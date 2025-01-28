package sphene.command;

import sphene.exception.OutOfListRangeException;
import sphene.exception.SaveException;
import sphene.component.TaskList;
import sphene.component.Ui;
import sphene.component.Storage;

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