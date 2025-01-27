package task;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    public Event(String content, LocalDateTime from, LocalDateTime to){
        super(content);
        this.from = from;
        this.to = to;
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + " (from: " + this.from.format(DateTimeFormatter.ofPattern("HH:mm dd MMM yyyy"))
                + ", to: " + this.to.format(DateTimeFormatter.ofPattern("HH:mm dd MMM yyyy"))
                + ")";
    }

    @Override
    public String serialize() {
        return "E" + "," + super.serialize()
                + "," + this.from.format(DateTimeFormatter.ISO_DATE_TIME)
                + "," + this.to.format(DateTimeFormatter.ISO_DATE_TIME);
    }
}
