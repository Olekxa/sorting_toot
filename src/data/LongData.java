package data;

import item.ILong;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LongData extends Data<ILong> {


    public LongData(File input) throws FileNotFoundException {
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
    public List<ILong> mapData(List<String> data) {
        return data
                .stream()
                .map(String::trim)
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .filter(x -> x.matches("-?\\d+(\\.\\d+)?"))
                .map(Long::parseLong)
                .map(ILong::new)
                .sorted(Comparator.comparingLong(ILong::getValue))
                .collect(Collectors.toList());
    }
}
