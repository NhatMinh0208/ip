package sphene.exception;

/**
 * Exception thrown when data cannot be saved to a file.
 */
public class SaveException extends SpheneException {
    private final String filePath;

    /**
     * Creates a new save exception.
     * @param filePath Path of the file where the exception occurs.
     */
    public SaveException(String filePath) {
        super("", "");
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "Failed to save to file " + filePath;
    }


    @Override
    public String getMessage() {
        return "My dear citizen, I could not save to the file " + this.filePath + " on Alexandria's memory.";
    }
}
