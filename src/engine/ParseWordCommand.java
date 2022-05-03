package engine;

import data.InputData;
import data.OutputData;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class ParseWordCommand extends Command {
    public ParseWordCommand(InputData inputData) {
        super(inputData);
        getInputData().readInput();
    }

    @Override
    public String execute() {
        List<String> words = getListOfWords();

        String biggest = getBiggest(words);

        int countItems = words.size();

        int countRepeats = getCountRepeats(words, biggest);

        int percent = Math.round((float) countRepeats / countItems * 100);

        return String.format("Total words: %d.\n" +
                "The longest word: %s (%d time(s), %d%%).", countItems, biggest, countRepeats, percent);
    }

    private int getCountRepeats(List<String> words, String biggest) {
        int countRepeats = words
                .stream()
                .filter(biggest::equals)
                .toList()
                .size();
        return countRepeats;
    }

    private String getBiggest(List<String> words) {
        String biggest = words
                .stream()
                .max(Comparator.comparingInt(String::length))
                .orElseThrow(IllegalArgumentException::new);
        return biggest;
    }

    private List<String> getListOfWords() {
        List<String> words = getInputData()
                .getList()
                .stream()
                .map(String::trim)
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .filter(x -> !x.equals(" "))
                .filter(x -> !x.equals(""))
                .toList();
        return words;
    }

}
