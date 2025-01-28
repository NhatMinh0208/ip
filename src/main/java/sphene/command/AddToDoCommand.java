package sphene.command;

import sphene.exception.SaveException;
import sphene.component.TaskList;
import sphene.component.Ui;
import sphene.component.Storage;
import sphene.task.ToDo;

public class AddToDoCommand extends Command {
    private final String content;

    public AddToDoCommand(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "todo " + this.content;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SaveException {
        tasks.addTask(new ToDo(content));
        storage.store(tasks.serialize());
    }
}
