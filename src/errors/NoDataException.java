package errors;

import java.util.Collections;


public class NoDataException extends CommandException{
    public NoDataException() {
        super(Collections.singletonList("No data type defined!"));
    }
}
