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

public class AddEventCommand extends Command {
    private final String content;
    private final LocalDateTime from;
    private final LocalDateTime to;

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
