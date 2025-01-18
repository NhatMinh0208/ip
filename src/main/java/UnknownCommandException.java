public class UnknownCommandException extends SpheneException {
    public UnknownCommandException(String command) {
        super(command, null);
    }

    @Override
    public String toString() {
        return "Unknown command error from command: " + this.getCommand();
    }

    @Override
    public String dialogue() {
        return "I don't know how to do this command: " + this.getCommand();
    }
}
