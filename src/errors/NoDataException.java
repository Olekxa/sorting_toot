package errors;

public class NoDataException extends CommandException {
    public NoDataException() {
        super("No data type defined!");
    }
}
