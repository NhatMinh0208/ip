package sphene.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A task with a time to complete by.
 */
public class Deadline extends Task {
    private final LocalDateTime deadlineTime;

    /**
     * Creates a new deadline task.
     * @param content The content of the deadline.
     * @param deadlineTime The deadline time.
     */
    public Deadline(String content, LocalDateTime deadlineTime) {
        super(content);
        this.deadlineTime = deadlineTime;
    }

    /**
     * Retrieves the deadline time.
     * @return The deadline time.
     */
    public LocalDateTime getDeadlineTime() {
        return this.deadlineTime;
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + this.deadlineTime.format(DateTimeFormatter.ofPattern("HH:mm dd MMM yyyy")) + ")";
    }

    @Override
    public String serialize() {
        return "D" + "," + super.serialize() + "," + this.deadlineTime.format(DateTimeFormatter.ISO_DATE_TIME);
    }

    @Override
    public boolean equals(Object other) {
        if (!(other instanceof Deadline)) {
            return false;
        }
        return super.equals(other)
                && this.deadlineTime.equals(((Deadline) other).deadlineTime);
    }
}
