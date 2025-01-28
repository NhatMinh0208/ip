package sphene.exception;

/**
 * Exception thrown when a command is not on the list of known commands.
 */
public class UnknownCommandException extends SpheneException {

    /**
     * Creates a new unknown command exception.
     * @param command The command where the exception occurs.
     */
    public UnknownCommandException(String command) {
        super(command, null);
    }

    @Override
    public String toString() {
        return "Unknown command error from command: " + this.getCommand();
    }

    @Override
    public String getMessage() {
        return "I don't know how to complete the request '" + this.getCommand() + "'.";
    }
}
