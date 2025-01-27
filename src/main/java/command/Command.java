package command;

import exception.OutOfListRangeException;
import exception.SaveException;
import component.TaskList;
import component.Ui;
import component.Storage;

public abstract class Command {
    public Command() {

    }

    @Override
    public String toString() {
        return "[command]";
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws SaveException, OutOfListRangeException {

    }
}
