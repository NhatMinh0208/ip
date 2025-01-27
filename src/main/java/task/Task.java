package task;

public abstract class Task {
    private final String content;
    private boolean done;

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

    public String serialize() {
        return (this.done ? "1" : "0") + "," + this.content;
    }

    public void markDone() {
        this.done = true;
    }

    public void unmarkDone() {
        this.done = false;
    }
}