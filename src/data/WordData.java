package data;

import errors.FileCommandException;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class WordData extends Data<String> {
    private final String typeName;
    public WordData(File input) throws FileCommandException {
        super(input);
        this.typeName = "words";
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
        return this.typeName;
    }
}
