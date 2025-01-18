public class SpheneException extends Exception {
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
        return "Sphene error from command: " + this.getCommand() + "with parameters: " + this.getParams();
    }

    public String dialogue() {
        return "There was an error when trying to perform the request: " + this.getCommand() + "with parameters: " + this.getParams();
    }
}
