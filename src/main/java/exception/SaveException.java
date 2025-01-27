package exception;

public class SaveException extends SpheneException {

    String filePath;

    public SaveException(String filePath) {
        super("","");
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
