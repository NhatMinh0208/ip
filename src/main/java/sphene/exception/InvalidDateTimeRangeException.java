package sphene.exception;

import java.time.LocalDateTime;

/**
 * Exception thrown when field(s) in a command do not form a valid datetime range.
 */
public class InvalidDateTimeRangeException extends SpheneException {
    private final String field;
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Creates a new invalid datetime range exception.
     * @param command The command where the exception occurs.
     * @param field The field(s) where the exception occurs.
     * @param from The start datetime of the range where the exception occurs.
     * @param to The end datetime of the range where the exception occurs.
     */
    public InvalidDateTimeRangeException(String command, String field, LocalDateTime from, LocalDateTime to) {
        super(command, "");
        this.field = field;
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "Field '" + this.field + "' in command '" + this.getCommand() + "' has range "
                + this.from + " -> "
                + this.to + ", not a valid datetime range";
    }

    @Override
    public String getMessage() {
        return "The field '" + this.field + "' in the request '" + this.getCommand() + "' has range "
                + this.from + " -> "
                + this.to
                + ". It is not a valid datetime range!";
    }
}
