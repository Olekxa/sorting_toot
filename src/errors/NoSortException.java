package errors;

public class NoSortException extends CommandException {
    public NoSortException() {
        super("No sorting type defined!");
    }
}
