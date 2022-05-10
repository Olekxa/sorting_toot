package errors;

import java.util.Collections;

public class NoSortException extends CommandException{
    public NoSortException() {
        super(Collections.singletonList("No sorting type defined!"));
    }
}
