package sphene.exception;

public class InvalidDateTimeException extends SpheneException {
    private final String field;
    private final String value;
    public InvalidDateTimeException(String command, String field, String value) {
        super(command, "");
        this.field = field;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Field '" + this.field + "' in sphene.command '" + this.getCommand() + "' has value " + this.value
                + ", not a valid time";
    }

    @Override
    public String getMessage() {
        return "The field '" + this.field + "' in the request '" + this.getCommand() + "' has value " + this.value
                + ". It is not a valid time!\n"
                + "Please use the ISO format YYYY-MM-DDTHH:MM:SS.";
    }
}
