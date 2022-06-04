package data;

import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LineData extends Data<String> {

    public LineData(File input)  {
        super(input);
    }

    @Override
    public List<String> mapData(List<String> data) {
        return data.stream()
                .sorted(Comparator.naturalOrder())
                .collect(Collectors.toList());
    }

    @Override
    public String getTypeName() {
        return "lines";
    }
}
