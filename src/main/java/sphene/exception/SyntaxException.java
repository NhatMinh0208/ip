package sphene.exception;

/**
 * Exception thrown when a command does not follow the given syntax.
 */
public class SyntaxException extends SpheneException {

    /**
     * Creates a new syntax exception.
     * @param command The command where the exception occurs.
     * @param params The parameters of the command.
     */
    public SyntaxException(String command, String params) {
        super(command, params);
    }

    @Override
    public String toString() {
        return "Syntax error from command: " + this.getCommand() + "with parameters: " + this.getParams();
    }

    @Override
    public String getMessage() {
        return "You didn't use the correct format for the request '" + this.getCommand() + "'!";
    }
}
