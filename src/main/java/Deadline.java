import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Deadline extends Task {
    private final LocalDateTime by;

    public Deadline(String content, String by) throws InvalidDateTimeException {
        super(content);
        try {
            this.by = LocalDateTime.parse(by, DateTimeFormatter.ISO_DATE_TIME);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException("event", "by", by);
        }
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + " (by: " + this.by.format(DateTimeFormatter.ofPattern("HH:mm dd MMM yyyy")) + ")";
    }

    @Override
    public String serialize() {
        return "D" + "," + super.serialize() + "," + this.by;
    }
}
