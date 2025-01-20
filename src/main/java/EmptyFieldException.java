public class EmptyFieldException extends SpheneException {
    private final String field;
    public EmptyFieldException(String command, String params, String field) {
        super(command, params);
        this.field = field;
    }

    @Override
    public String toString() {
        return "Field '" + this.field + "' in command '" + this.getCommand() + "' should not be empty";
    }

    @Override
    public String dialogue() {
        return "The field '" + this.field + "' in the request '" + this.getCommand() + "' should not be empty.";
    }
}
