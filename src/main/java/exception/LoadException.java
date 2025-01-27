package exception;

public class LoadException extends SpheneException {

    String filePath;

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
