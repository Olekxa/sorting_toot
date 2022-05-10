package errors;

import java.util.List;

public class CommandException extends RuntimeException {
    private List<String> errors;

    public CommandException(List<String> errors) {
        this.errors = errors;
    }

    public List<String> getErrors() {
        return errors;
    }
}
