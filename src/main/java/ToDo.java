public class ToDo extends Task {
    public ToDo(String content) {
        super(content);
    }

    @Override
    public String toString() {
        return "[T]" + super.toString();
    }

    @Override
    public String serialize() {
        return "T" + "," + super.serialize();
    }
}
