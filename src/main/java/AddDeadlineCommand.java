import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class AddDeadlineCommand extends Command {
    private final String content;
    private final LocalDateTime by;

    public AddDeadlineCommand(String content, String by) throws InvalidDateTimeException {
        this.content = content;
        try {
            this.by = LocalDateTime.parse(by, DateTimeFormatter.ISO_DATE_TIME);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException("deadline", "by", by);
        }
    }

    @Override
    public String toString() {
        return "deadline " + this.content + " /by " + this.by;
    }
}
