package errors;

public class CommandException extends RuntimeException {
    private final String error;

    public CommandException(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }
}
