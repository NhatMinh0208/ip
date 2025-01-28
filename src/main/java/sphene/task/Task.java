package sphene.task;

/**
 * A generic task.
 */
public abstract class Task {
    private final String content;
    private boolean done;

    /**
     * Creates a new generic task.
     * @param content The content of the task.
     */
    public Task(String content) {
        this.content = content;
        this.done = false;
    }

    private String getStatusIcon() {
        return "[" + (this.done ? "X" : " ") + "]";
    }

    public String getContent() {
        return this.content;
    }

    @Override
    public String toString() {
        return this.getStatusIcon() + " " + this.content;
    }

    /**
     * Serializes the task into a string that can be stored.
     * @return The serialized task string.
     */
    public String serialize() {
        return (this.done ? "1" : "0") + "," + this.content;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.done = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmarkDone() {
        this.done = false;
    }
}