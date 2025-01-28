package sphene.task;

/**
 * A generic task.
 */
public abstract class Task {
    private final String content;
    private boolean isDone;

    /**
     * Creates a new generic task.
     * @param content The content of the task.
     */
    public Task(String content) {
        this.content = content;
        this.isDone = false;
    }

    private String getStatusIcon() {
        return "[" + (this.isDone ? "X" : " ") + "]";
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
        return (this.isDone ? "1" : "0") + "," + this.content;
    }

    /**
     * Marks the task as done.
     */
    public void markDone() {
        this.isDone = true;
    }

    /**
     * Marks the task as not done.
     */
    public void unmarkDone() {
        this.isDone = false;
    }
}
