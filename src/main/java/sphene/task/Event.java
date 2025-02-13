package sphene.task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * A task with a start and end time.
 */
public class Event extends Task {
    private final LocalDateTime startTime;
    private final LocalDateTime endTIme;

    /**
     * Creates a new event task.
     * @param content The content of the event.
     * @param startTime The event start time.
     * @param endTIme The event end time.
     */
    public Event(String content, LocalDateTime startTime, LocalDateTime endTIme) {
        super(content);
        this.startTime = startTime;
        this.endTIme = endTIme;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + this.startTime.format(DateTimeFormatter.ofPattern("HH:mm dd MMM yyyy"))
                + ", to: " + this.endTIme.format(DateTimeFormatter.ofPattern("HH:mm dd MMM yyyy"))
                + ")";
    }

    @Override
    public String serialize() {
        return "E" + "," + super.serialize()
                + "," + this.startTime.format(DateTimeFormatter.ISO_DATE_TIME)
                + "," + this.endTIme.format(DateTimeFormatter.ISO_DATE_TIME);
    }
}
