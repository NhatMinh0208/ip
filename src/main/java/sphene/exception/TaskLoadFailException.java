package sphene.exception;

/**
 * Exception thrown when a serialized task string cannot be parsed.
 */
public class TaskLoadFailException extends SpheneException {
    private final String taskString;

    /**
     * Creates a new task load fail exception.
     * @param taskString The serialized task string where the exception occurs.
     */
    public TaskLoadFailException(String taskString) {
        super("","");
        this.taskString = taskString;
    }

    @Override
    public String toString() {
        return "Failed to load task " + this.taskString + " from task list file";
    }


    @Override
    public String getMessage() {
        return "My dear citizen, I could not retrieve a sphene.task from Alexandria's memory.";
    }

}
