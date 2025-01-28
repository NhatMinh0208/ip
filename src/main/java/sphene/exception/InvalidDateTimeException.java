package sphene.exception;

/**
 * Exception thrown when a field in a command is not a valid datetime.
 */
public class InvalidDateTimeException extends SpheneException {
    private final String field;
    private final String value;

    /**
     * Creates a new invalid datetime exception.
     * @param command The command where the exception occurs.
     * @param field The field where the exception occurs.
     * @param value The value of the field.
     */
    public InvalidDateTimeException(String command, String field, String value) {
        super(command, "");
        this.field = field;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Field '" + this.field + "' in command '" + this.getCommand() + "' has value " + this.value
                + ", not a valid time";
    }

    @Override
    public String getMessage() {
        return "The field '" + this.field + "' in the request '" + this.getCommand() + "' has value " + this.value
                + ". It is not a valid time!\n"
                + "Please use the ISO format YYYY-MM-DDTHH:MM:SS.";
    }
}
