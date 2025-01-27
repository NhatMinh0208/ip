public class ExitCommand extends Command {
    public ExitCommand() {

    }

    @Override
    public String toString() {
        return "bye";
    }

    public boolean isExit() {
        return true;
    }
}
