package errors;

import java.util.ArrayList;


public class FileCommandException extends CommandException {

    public FileCommandException() {
        super(new ArrayList<>());
    }
}
