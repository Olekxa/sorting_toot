package engine;

import data.InputData;

public class ParseUnknownCommand extends Command {

    public ParseUnknownCommand(InputData inputData) {
        super(inputData);
    }

    @Override
    public String execute() {
        return String.format("Unknown command %s", getInputData());
    }

}

