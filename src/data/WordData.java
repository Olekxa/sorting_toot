package data;

import errors.FileCommandException;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class WordData extends Data<String> {
    public WordData(File input) throws FileCommandException {
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
                .toList();
    }
}
