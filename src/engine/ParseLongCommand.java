package engine;

import data.LongData;
import utils.SortType;
import utils.Utils;

import java.util.*;
import java.util.stream.Collectors;


public class ParseLongCommand extends Command<LongData> {

    public ParseLongCommand(LongData input, SortType sortType) {
        super(input, sortType);
    }

    @Override
    public String execute() {
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

    private List<String> getInvalidList() {
        List<String> invalidMessage = getInput()
                .getInvalid()
                .stream()
                .map(s -> String.format("\"%s\" is not a long. It will be skipped.\n", s))
                .collect(Collectors.toList());
        return Collections
                .singletonList(String.join("", invalidMessage));
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

    private List<String> sortByCount(List<Long> longs) {
        Map<Long, Integer> map = new LinkedHashMap<>();
        List<String> resulList = new ArrayList<>();
        for (Long iLong : longs) {
            map.put(iLong, map.getOrDefault(iLong, 0) + 1);
        }
        Map<Long, Integer> sortedMap = Utils.sortByValue(map);

        for (Map.Entry<Long, Integer> entry : sortedMap.entrySet()) {

            int percent = Math.round((float) entry.getValue() / longs.size() * 100);
            resulList.add(String.format("%d: %d time(s), %d%%", entry.getKey(), entry.getValue(), percent));
        }
        return resulList;
    }
}