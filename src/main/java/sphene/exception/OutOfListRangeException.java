package sphene.exception;

/**
 * Exception thrown when a field in a command is outside the range of valid list indices.
 */
public class OutOfListRangeException extends SpheneException {
    private final String field;
    private final int value;

    /**
     * Creates a new out of list range exception.
     * @param command The command where the exception occurs.
     * @param field The field where the exception occurs.
     * @param value The value of the field.
     */
    public OutOfListRangeException(String command, String field, int value) {
        super(command, "");
        this.field = field;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Field '" + this.field + "' in sphene.command '" + this.getCommand() + "' has value " + this.value
                + ", outside list range";
    }

    @Override
    public String getMessage() {
        return "The field '" + this.field + "' in the request '" + this.getCommand() + "' has value " + this.value
                + ". It is outside the range of valid list indices!";
    }
}
