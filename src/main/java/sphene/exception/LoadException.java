package sphene.exception;

/**
 * Exception thrown when data cannot be loaded from a file.
 */
public class LoadException extends SpheneException {
    private final String filePath;

    /**
     * Creates a new load exception.
     * @param filePath Path of the file where the exception occurs.
     */
    public LoadException(String filePath) {
        super("","");
        this.filePath = filePath;
    }

    @Override
    public String toString() {
        return "Failed to load file " + filePath;
    }


    @Override
    public String getMessage() {
        return "My dear citizen, I could not retrieve the file " + this.filePath + " from Alexandria's memory.";
    }

}
