package sphene.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import sphene.exception.InvalidDateTimeException;
import sphene.exception.SaveException;
import sphene.component.TaskList;
import sphene.component.Ui;
import sphene.component.Storage;
import sphene.task.Deadline;

/**
 * Command for adding a deadline task.
 */
public class AddDeadlineCommand extends Command {
    private final String content;
    private final LocalDateTime by;

    /**
     * Creates a new add deadline command.
     * @param content Content of the deadline.
     * @param by String describing deadline time.
     * @throws InvalidDateTimeException If `by` cannot be parsed into a valid datetime.
     */
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

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SaveException {
        tasks.addTask(new Deadline(content, by));
        storage.store(tasks.serialize());
    }
}
