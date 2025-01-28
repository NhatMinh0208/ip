package sphene.task;

/**
 * A simple task with no other information.
 */
public class ToDo extends Task {
    /**
     * Creates a new todo task.
     * @param content The content of the todo.
     */
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
