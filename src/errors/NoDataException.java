package errors;

import java.util.Collections;
import java.util.List;

public class NoDataException extends CommandException{
    public NoDataException() {
        super(Collections.singletonList("No data type defined!"));
    }
}
