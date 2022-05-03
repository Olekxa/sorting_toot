package engine;

import data.InputData;
import data.OutputData;

import java.util.*;

public class ParseLongCommand extends Command {

    public ParseLongCommand(InputData inputData) {
        super(inputData);
        getInputData().readInput();
    }

    @Override
    public String execute() {
        List<Long> innStream = getList();

        long maxNumber = getMaxNumber(innStream);

        int countItems = innStream.size();

        int countRepeats = getCountRepeats(innStream, maxNumber);

        int percent = Math.round((float) countRepeats / countItems * 100);

        return String.format("Total numbers: %d.\n" +
                "The greatest number: %d (%d time(s), %d%%).", countItems, maxNumber, countRepeats, percent);
    }

    private int getCountRepeats(List<Long> innStream, long maxNumber) {
        int countRepeats = innStream.stream()
                .filter(x -> Objects.equals(x, maxNumber))
                .toList()
                .size();
        return countRepeats;
    }

    private long getMaxNumber(List<Long> innStream) {
        long maxNumber = innStream.stream()
                .max(Long::compare)
                .orElseThrow(NoSuchElementException::new);
        return maxNumber;
    }

    private List<Long> getList() {
        List<Long> innStream = getInputData()
                .getList()
                .stream()
                .map(String::trim)
                .flatMap(s -> Arrays.stream(s.split("\\D")))
                .filter(x -> x.matches("-?\\d+(\\.\\d+)?"))
                .map(Long::parseLong)
                .toList();
        return innStream;
    }

}


