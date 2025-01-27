public class ListCommand extends Command {
    public ListCommand() {

    }

    @Override
    public String toString() {
        return "list";
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SaveException, OutOfListRangeException {
        ui.print(tasks.toString());
    }
}
