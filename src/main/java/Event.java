import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Event extends Task {
    private final LocalDateTime from;
    private final LocalDateTime to;

    public Event(String content, String from, String to) throws InvalidDateTimeException {
        super(content);
        try {
            this.from = LocalDateTime.parse(from, DateTimeFormatter.ISO_DATE_TIME);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException("event", "from", from);
        }
        try {
            this.to = LocalDateTime.parse(to, DateTimeFormatter.ISO_DATE_TIME);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException("event", "to", to);
        }

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
        return "E" + "," + super.serialize() + "," + this.from + "," + this.to;
    }
}
