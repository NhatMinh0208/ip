package exception;

public class OutOfListRangeException extends SpheneException {
    private final String field;
    private final int value;
    public OutOfListRangeException(String command, String field, int value) {
        super(command, "");
        this.field = field;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Field '" + this.field + "' in command '" + this.getCommand() + "' has value " + this.value
                + ", outside list range";
    }

    @Override
    public String getMessage() {
        return "The field '" + this.field + "' in the request '" + this.getCommand() + "' has value " + this.value
                + ". It is outside the range of valid list indices!";
    }
}
