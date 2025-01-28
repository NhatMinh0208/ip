package sphene.exception;

public class SyntaxException extends SpheneException {

    public SyntaxException(String command, String params) {
        super(command, params);
    }

    @Override
    public String toString() {
        return "Syntax error from sphene.command: " + this.getCommand() + "with parameters: " + this.getParams();
    }

    @Override
    public String getMessage() {
        return "You didn't use the correct format for the request '" + this.getCommand() + "'!";
    }
}
