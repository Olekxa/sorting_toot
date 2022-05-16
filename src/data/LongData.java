package data;

import errors.FileCommandException;


import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LongData extends Data<Long> {


    public LongData(File input) throws FileCommandException {
        super(input);
    }

    @Override
    public List<String> mapInvalid(List<String> data) {
        return data
                .stream()
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .filter(x -> x.matches("[a-zA-Z]+"))
                .collect(Collectors.toList());
    }

    @Override
    public List<Long> mapData(List<String> data) {
        return data
                .stream()
                .map(String::trim)
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .filter(x -> x.matches("-?\\d+(\\.\\d+)?"))
                .map(Long::parseLong)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
    }
}
