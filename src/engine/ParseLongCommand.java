package engine;

import data.Data;

public class ParseLongCommand extends Command{
    private String input;

    public ParseLongCommand(Application app, Data data, String input) {
        super(app, data);
        this.input = input;
    }

    @Override
    void execute() {

    }
}
