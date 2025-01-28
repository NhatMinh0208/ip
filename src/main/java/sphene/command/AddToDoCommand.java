package sphene.command;

import sphene.component.Storage;
import sphene.component.TaskList;
import sphene.component.Ui;
import sphene.exception.SaveException;
import sphene.task.ToDo;

/**
 * Command for adding a todo task.
 */
public class AddToDoCommand extends Command {
    private final String content;

    /**
     * Crates a new add todo command.
     * @param content Content of the todo.
     */
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
