public class SyntaxException extends SpheneException {

    public SyntaxException(String command, String params) {
        super(command, params);
    }

    @Override
    public String toString() {
        return "Syntax error from command: " + this.getCommand() + "with parameters: " + this.getParams();
    }
}
