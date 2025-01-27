public class AddToDoCommand extends Command {
    private final String content;

    public AddToDoCommand(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "todo " + this.content;
    }
}
