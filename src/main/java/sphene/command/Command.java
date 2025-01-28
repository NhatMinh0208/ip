package sphene.command;

import sphene.component.Storage;
import sphene.component.TaskList;
import sphene.component.Ui;
import sphene.exception.OutOfListRangeException;
import sphene.exception.SaveException;

public abstract class Command {
    public Command() {

    }

    @Override
    public String toString() {
        return "[sphene.command]";
    }

    public boolean isExit() {
        return false;
    }

    public void execute(TaskList tasks, Ui ui, Storage storage) throws SaveException, OutOfListRangeException {

    }
}
