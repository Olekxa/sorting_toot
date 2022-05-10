package errors;

import java.util.List;
import java.util.stream.Collectors;

public class UnknownCommandException extends CommandException {
    public UnknownCommandException(List<String> errors) {
        super(errors);
    }

    public List<String> getMessages() {
        return getErrors()
                .stream()
                .map(s -> String.format("\"%s\" is not a valid parameter. It will be skipped.", s))
                .collect(Collectors.toList());
    }
}
