package sphene.exception;

public class TaskLoadFailException extends SpheneException {

    private final String taskString;

    public TaskLoadFailException(String taskString) {
        super("","");
        this.taskString = taskString;
    }

    @Override
    public String toString() {
        return "Failed to load sphene.task " + this.taskString + " from sphene.task list file";
    }


    @Override
    public String getMessage() {
        return "My dear citizen, I could not retrieve a sphene.task from Alexandria's memory.";
    }

}
