package sphene.exception;

public class UnknownCommandException extends SpheneException {
    public UnknownCommandException(String command) {
        super(command, null);
    }

    @Override
    public String toString() {
        return "Unknown sphene.command error from sphene.command: " + this.getCommand();
    }

    @Override
    public String getMessage() {
        return "I don't know how to complete the request '" + this.getCommand() + "'.";
    }
}
