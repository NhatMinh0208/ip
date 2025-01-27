import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Deadline extends Task {
    private final LocalDateTime by;

    public Deadline(String content, String by) {
        super(content);
        this.by = LocalDateTime.parse(by);
    }

    @Override
    public String toString() {
        return "[D]" + super.toString()
                + "(by: " + this.by.format(DateTimeFormatter.ofPattern("HH:mm dd MMM yyyy")) + ")";
    }

    @Override
    public String serialize() {
        return "D" + "," + super.serialize() + "," + this.by;
    }
}
