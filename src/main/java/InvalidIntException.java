public class InvalidIntException extends SpheneException {
    private final String field;
    private final String value;
    public InvalidIntException(String command, String field, String value) {
        super(command, "");
        this.field = field;
        this.value = value;
    }

    @Override
    public String toString() {
        return "Field '" + this.field + "' in command '" + this.getCommand() + "' has value " + this.value
                + ", not a valid integer";
    }

    @Override
    public String getMessage() {
        return "The field '" + this.field + "' in the request '" + this.getCommand() + "' has value " + this.value
                + ". It is not a valid number!\n";
    }
}
