package engine;

import data.LongData;
import utils.SortType;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class ParseLongCommand extends Command<Long, LongData> {

    public ParseLongCommand(LongData input, SortType sortType, File outputFile) {
        super(input, sortType, outputFile);
    }

    @Override
    protected String process() {
        List<Long> data = getInput().getData();
        List<String> result;
        if (SortType.BY_COUNT.equals(getSortType())) {
            result = sortByCount(data);
        } else {
            result = sortByNatural(data);
        }
        List<String> refactored = getInvalidList();
        String printInvalid = String.join("", refactored);
        String total = String.format("Total numbers: %d.", data.size());
        String sort = String.join("\n", result);
        return String.format("%s%s\n%s", printInvalid, total, sort);
    }

    private List<String> sortByNatural(List<Long> data) {
        List<String> stringList = new ArrayList<>();
        for (Long number : data) {
            stringList.add(number.toString());
        }
        return Collections.singletonList(
                String.format(
                        "Sorted data: %s", String.join(" ", stringList)
                )
        );
    }

    @Override
    public String formatter(Long aLong, int number, int percent) {
        return String.format("%s: %d time(s), %d%%", aLong, number, percent);
    }

    private List<String> getInvalidList() {
        List<String> invalidMessage = getInput()
                .getInvalid()
                .stream()
                .map(s -> String.format("\"%s\" is not a long. It will be skipped.\n", s))
                .collect(Collectors.toList());
        return Collections
                .singletonList(String.join("", invalidMessage));
    }
}