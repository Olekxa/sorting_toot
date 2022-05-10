package errors;

import java.util.Collections;
import java.util.List;

public class NoSortException extends CommandException{
    public NoSortException() {
        super(Collections.singletonList("No sorting type defined!"));
    }
}
