package engine;

import data.LineData;
import utils.SortType;
import utils.Utils;

import java.io.File;
import java.util.*;


public class ParseLineCommand extends Command<String, LineData> {
    public ParseLineCommand(LineData input, SortType sortType, File outputFile) {
        super(input, sortType, outputFile);
    }

    @Override
    public String process() {
        List<String> data = getInput().getData();
        List<String> result;
        if (SortType.BY_COUNT.equals(getSortType())) {
            result = sortByCount(data);
        } else {
            result = sortByNatural(data);
        }
        String total = String.format("Total lines: %d.", data.size());
        String sort = String.join("\n", result);
        return String.format("%s\n%s", total, sort);
    }

    @Override
    public String formatter(String s, int number, int percent) {
        return String.format("%s: %d time(s), %d%%", s, number, percent);
    }

    private List<String> sortByNatural(List<String> data) {
        return Collections.singletonList(
                String.format(
                        "Sorted data: %s", String.join(" ", data)
                )
        );
    }
}