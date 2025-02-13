package sphene.command;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

import sphene.component.Storage;
import sphene.component.TaskList;
import sphene.component.Ui;
import sphene.exception.InvalidDateTimeException;
import sphene.exception.SaveException;
import sphene.task.Deadline;

/**
 * Command for adding a deadline task.
 */
public class AddDeadlineCommand extends Command {
    private final String content;
    private final LocalDateTime deadlineTime;

    /**
     * Creates a new add deadline command.
     * @param content Content of the deadline.
     * @param deadlineTime String describing deadline time.
     * @throws InvalidDateTimeException If `deadlineTime` cannot be parsed into a valid datetime.
     */
    public AddDeadlineCommand(String content, String deadlineTime) throws InvalidDateTimeException {
        this.content = content;
        try {
            this.deadlineTime = LocalDateTime.parse(deadlineTime, DateTimeFormatter.ISO_DATE_TIME);
        } catch (DateTimeParseException e) {
            throw new InvalidDateTimeException("deadline", "by", deadlineTime);
        }
    }

    @Override
    public String toString() {
        return "deadline " + this.content + " /by " + this.deadlineTime;
    }

    @Override
    public void execute(TaskList tasks, Ui ui, Storage storage) throws SaveException {
        tasks.addTask(new Deadline(content, deadlineTime));
        storage.store(tasks.serialize());
    }
}
