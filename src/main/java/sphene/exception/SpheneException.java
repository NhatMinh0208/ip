package sphene.exception;

/**
 * Generic bot exception.
 */
public abstract class SpheneException extends Exception {
    private final String command;
    private final String params;

    /**
     * Creates a new bot exception.
     * @param command The command where the exception occurs.
     * @param params The parameters of the command.
     */
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
        return "Sphene error from command: " + this.getCommand() + "with parameters: " + this.getParams();
    }

    @Override
    public String getMessage() {
        return "Something has gone wrong while completing the request '" + this.getCommand() + "' with parameters: " + this.getParams();
    }
}
