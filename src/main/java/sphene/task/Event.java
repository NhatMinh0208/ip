package sphene.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A task with a start and end time.
 */
public class Event extends Task {
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    /**
     * Creates a new event task.
     * @param content The content of the event.
     * @param startTime The event start time.
     * @param endTime The event end time.
     */
    public Event(String content, LocalDateTime startTime, LocalDateTime endTime) {
        super(content);
        assert !startTime.isAfter(endTime);
        this.startTime = startTime;
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + this.startTime.format(DateTimeFormatter.ofPattern("HH:mm dd MMM yyyy"))
                + ", to: " + this.endTime.format(DateTimeFormatter.ofPattern("HH:mm dd MMM yyyy"))
                + ")";
    }

    @Override
    public String serialize() {
        return "E" + "," + super.serialize()
                + "," + this.startTime.format(DateTimeFormatter.ISO_DATE_TIME)
                + "," + this.endTime.format(DateTimeFormatter.ISO_DATE_TIME);
    }
}
