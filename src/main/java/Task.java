public class Task {
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

    public void markDone() {
        this.done = true;
    }

    public void unmarkDone() {
        this.done = false;
    }
}
