package engine;

import data.Data;
import utils.SortType;

public class ParseUnknownCommand extends Command {


    public ParseUnknownCommand(Data input, SortType sortType) {
        super(input, sortType);
    }

    @Override
    public String execute() {
        return String.format("Unknown command %s", getInput());
    }
}

