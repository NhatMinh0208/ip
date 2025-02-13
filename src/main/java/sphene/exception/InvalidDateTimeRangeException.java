package sphene.exception;

import java.time.LocalDateTime;

/**
 * Exception thrown when field(s) in a command do not form a valid datetime range.
 */
public class InvalidDateTimeRangeException extends SpheneException {
    private final String field;
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    /**
     * Creates a new invalid datetime range exception.
     * @param command The command where the exception occurs.
     * @param field The field(s) where the exception occurs.
     * @param startTime The start datetime of the range where the exception occurs.
     * @param endTime The end datetime of the range where the exception occurs.
     */
    public InvalidDateTimeRangeException(String command, String field,
                                         LocalDateTime startTime, LocalDateTime endTime) {
        super(command, "");
        this.field = field;
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "Field '" + this.field + "' in command '" + this.getCommand() + "' has range "
                + this.startTime + " -> "
                + this.endTime + ", not a valid datetime range";
    }

    @Override
    public String getMessage() {
        return "The field '" + this.field + "' in the request '" + this.getCommand() + "' has range "
                + this.startTime + " -> "
                + this.endTime
                + ". It is not a valid datetime range!";
    }
}
