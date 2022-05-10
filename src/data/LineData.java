package data;

import item.ILine;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class LineData extends Data<ILine> {
    public LineData(File input) throws FileNotFoundException {
        super(input);
    }

    @Override
    public List<ILine> mapData(List<String> data) {
        return data.stream()
                .map(ILine::new)
                .sorted(Comparator.comparing(ILine::getValue))
                .collect(Collectors.toList());
    }
}
