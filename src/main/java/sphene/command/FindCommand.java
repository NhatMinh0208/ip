package sphene.command;

import sphene.component.Storage;
import sphene.component.TaskList;
import sphene.component.Ui;

/**
 * Command to search for tasks in the list.
 */
public class FindCommand extends Command {
    private final String query;

    /**
     * Creates a new find command.
     * @param query The query string.
     */
    public FindCommand(String query) {
        this.query = query;
    }

    @Override
    public String toString() {
        return "find " + this.query;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) {
        ui.print(tasks.find(query).toString());
    }
}
