import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    public Event(String content, String from, String to) {
        super(content);
        this.from = LocalDateTime.parse(from);
        this.to = LocalDateTime.parse(to);
    }

    @Override
    public String toString() {
        return "[E]" + super.toString()
                + "(from: " + this.from.format(DateTimeFormatter.ofPattern("HH:mm dd MMM yyyy"))
                + ", to: " + this.to.format(DateTimeFormatter.ofPattern("HH:mm dd MMM yyyy"))
                + ")";
    }

    @Override
    public String serialize() {
        return "E" + "," + super.serialize() + "," + this.from + "," + this.to;
    }
}
