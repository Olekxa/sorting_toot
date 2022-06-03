package data;

import errors.CommandException;
import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LongData extends Data<Long> {

    public LongData(File input) throws CommandException {
        super(input);
    }

    public void mapInvalid(List<String> data) {
        data
                .stream()
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .filter(x -> x.matches(".*\\D.*"))
                .map(s -> String.format("\"%s\" is not a long. It will be skipped.\n", s))
                .forEach(System.out::print);
    }

    @Override
    public List<Long> mapData(List<String> data) {
        mapInvalid(data);
        return data
                .stream()
                .map(String::trim)
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .filter(x -> x.matches("-?\\d+(\\.\\d+)?"))
                .map(Long::parseLong)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
    }

    @Override
    public String getTypeName() {
        return "numbers";
    }
}
