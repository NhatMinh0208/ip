package sphene.command;

/**
 * Command to exit from the bot.
 */
public class ExitCommand extends Command {
    /**
     * Creates a new exit command.
     */
    public ExitCommand() {

    }

    @Override
    public String toString() {
        return "bye";
    }

    @Override
    public boolean isExit() {
        return true;
    }
}
