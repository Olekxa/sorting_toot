package errors;

import java.util.List;
import java.util.stream.Collectors;

@Deprecated
public class UnknownCommandException extends CommandException {
    private List<String> errors;

    public UnknownCommandException(List<String> errors) {
        super("");
        this.errors = errors;
    }

    public List<String> getMessages() {
        return errors
                .stream()
                .map(s -> String.format("\"%s\" is not a valid parameter. It will be skipped.", s))
                .collect(Collectors.toList());
    }
}
