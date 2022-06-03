package data;

import errors.CommandException;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class WordData extends Data<String> {

    public WordData(File input) throws CommandException {
        super(input);
    }

    @Override
    public List<String> mapData(List<String> data) {
        return data
                .stream()
                .map(String::trim)
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .filter(x -> !x.equals(" "))
                .filter(x -> !x.equals(""))
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
    }

    @Override
    public String getTypeName() {
        return "words";
    }
}
