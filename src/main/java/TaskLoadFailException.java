public class TaskLoadFailException extends SpheneException {

    private final String taskString;

    public TaskLoadFailException(String taskString) {
        super("","");
        this.taskString = taskString;
    }

    @Override
    public String toString() {
        return "Failed to load task " + this.taskString + "from task list file";
    }


    @Override
    public String getMessage() {
        return "My dear citizen, I could not retrieve a task from my memory.";
    }

}
