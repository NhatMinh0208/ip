package sphene.exception;

/**
 * Exception thrown when a field in a command is empty.
 */
public class EmptyFieldException extends SpheneException {
    private final String field;

    /**
     * Creates a new empty field exception.
     * @param command The command where the exception occurs.
     * @param params The parameters of the command.
     * @param field The field where the exception occurs.
     */
    public EmptyFieldException(String command, String params, String field) {
        super(command, params);
        this.field = field;
    }

    @Override
    public String toString() {
        return "Field '" + this.field + "' in command '" + this.getCommand() + "' should not be empty";
    }

    @Override
    public String getMessage() {
        return "The field '" + this.field + "' in the request '" + this.getCommand() + "' should not be empty.";
    }
}
