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
    private final LocalDateTime startTime;
    private final LocalDateTime endTime;

    /**
     * Creates a new add event command.
     * @param content Content of the event.
     * @param startTime String describing event start time.
     * @param endTime String describing event end time.
     * @throws InvalidDateTimeException If `startTime` or `endTime` cannot be parsed into a valid datetime.
     * @throws InvalidDateTimeRangeException If `startTime` and `endTime` do not form a valid datetime range.
     */
    public AddEventCommand(String content, String startTime, String endTime)
            throws InvalidDateTimeException, InvalidDateTimeRangeException {
        this.content = content;
        try {
            this.startTime = LocalDateTime.parse(startTime, DateTimeFormatter.ISO_DATE_TIME);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException("event", "from", startTime);
        }
        try {
            this.endTime = LocalDateTime.parse(endTime, DateTimeFormatter.ISO_DATE_TIME);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException("event", "to", endTime);
        }
        if (this.startTime.isAfter(this.endTime)) {
            throw new InvalidDateTimeRangeException("event", "from_to", this.startTime, this.endTime);
        }
    }

    @Override
    public String toString() {
        return "event " + this.content + " /from " + this.startTime + " /to " + this.endTime;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SaveException {
        tasks.addTask(new Event(content, startTime, endTime));
        storage.store(tasks.serialize());
        ui.print("You now have the following tasks:\n" + tasks.toString());
    }
}
