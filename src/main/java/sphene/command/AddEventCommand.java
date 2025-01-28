package sphene.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import sphene.component.Storage;
import sphene.component.TaskList;
import sphene.component.Ui;
import sphene.exception.InvalidDateTimeException;
import sphene.exception.InvalidDateTimeRangeException;
import sphene.exception.SaveException;
import sphene.task.Event;

/**
 * Command for adding an event task.
 */
public class AddEventCommand extends Command {
    private final String content;
    private final LocalDateTime from;
    private final LocalDateTime to;

    /**
     * Creates a new add event command.
     * @param content Content of the event.
     * @param from String describing event start time.
     * @param to String describing event end  time.
     * @throws InvalidDateTimeException If `from` or `to` cannot be parsed into a valid datetime.
     * @throws InvalidDateTimeRangeException If `from` and `to` do not form a valid datetime range.
     */
    public AddEventCommand(String content, String from, String to)
            throws InvalidDateTimeException, InvalidDateTimeRangeException {
        this.content = content;
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
        if (this.from.isAfter(this.to)) {
            throw new InvalidDateTimeRangeException("event", "from_to", this.from, this.to);
        }
    }

    @Override
    public String toString() {
        return "event " + this.content + " /from " + this.from + " /to " + this.to;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SaveException {
        tasks.addTask(new Event(content, from, to));
        storage.store(tasks.serialize());
    }
}
