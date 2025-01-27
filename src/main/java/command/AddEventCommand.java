package command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import exception.InvalidDateTimeException;
import exception.SaveException;
import component.TaskList;
import component.Ui;
import component.Storage;
import task.Event;

public class AddEventCommand extends Command {
    private final String content;
    private final LocalDateTime from;
    private final LocalDateTime to;

    public AddEventCommand(String content, String from, String to) throws InvalidDateTimeException {
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
