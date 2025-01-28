package sphene.exception;

public abstract class SpheneException extends Exception {
    private final String command;
    private final String params;

    public SpheneException(String command, String params) {
        this.command = command;
        this.params = params;
    }

    public String getCommand() {
        return command;
    }

    public String getParams() {
        return params;
    }

    @Override
    public String toString() {
        return "sphene.Sphene error from sphene.command: " + this.getCommand() + "with parameters: " + this.getParams();
    }

    @Override
    public String getMessage() {
        return "Something has gone wrong while completing the request '" + this.getCommand() + "' with parameters: " + this.getParams();
    }
}
