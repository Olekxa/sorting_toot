package data;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class LongData extends Data<Long> {

    public LongData(File input) {
        super(input);
    }

    @Override
    public List<Long> mapData(List<String> data) {
        return data
                .stream()
                .map(String::trim)
                .flatMap(s -> Arrays.stream(s.split(" ")))
                .map(x -> {
                    try {
                        return Long.parseLong(x);
                    } catch (NumberFormatException e) {
                        System.out.printf("\"%s\" is not a long. It will be skipped.\n", x);
                        return null;
                    }
                })
                .filter(Objects::nonNull)
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
    }

    @Override
    public String getTypeName() {
        return "numbers";
    }
}
