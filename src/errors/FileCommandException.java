package errors;



public class FileCommandException extends CommandException {
    public FileCommandException() {
        super("File not found");
    }
}
