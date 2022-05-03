package engine;

import data.InputData;
import data.OutputData;

import java.util.Comparator;

public class ParseLineCommand extends Command {

    public ParseLineCommand(InputData inputData) {
        super(inputData);
        getInputData().readInput();
    }

    @Override
    public String execute() {
        int countItems = getInputData()
                .getList()
                .size();

        String biggest = getString();

        int countRepeats = getCountRepeats(biggest);

        int percent = Math.round((float) countRepeats / countItems * 100);

        return String.format("""
                Total lines: %d.
                The longest line:
                 %s\s
                (%d time(s), %d%%).""", countItems, biggest, countRepeats, percent);
    }

    private int getCountRepeats(String biggest) {
        int countRepeats = getInputData()
                .getList()
                .stream()
                .filter(biggest::equals)
                .toList()
                .size();
        return countRepeats;
    }

    private String getString() {
        String biggest = getInputData()
                .getList()
                .stream()
                .max(Comparator.comparingInt(String::length))
                .orElseThrow(IllegalArgumentException::new);
        return biggest;
    }

}
