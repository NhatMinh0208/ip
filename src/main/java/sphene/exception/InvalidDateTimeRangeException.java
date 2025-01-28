package sphene.exception;

import java.time.LocalDateTime;

public class InvalidDateTimeRangeException extends SpheneException {
    private final String field;
    private final LocalDateTime from;
    private final LocalDateTime to;
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
