package data;

import errors.FileCommandException;

import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LineData extends Data<String> {
    private final String typeName;

    public LineData(File input) throws FileCommandException {
        super(input);
        typeName = "lines";
    }

    @Override
    public List<String> mapData(List<String> data) {
        return data.stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
    }

    @Override
    public String getTypeName() {
        return this.typeName;
    }
}
