package engine;

import data.LineData;
import item.ILine;
import utils.SortType;
import utils.Utils;

import java.util.*;


public class ParseLineCommand extends Command<LineData> {
    public ParseLineCommand(LineData input, SortType sortType) {
        super(input, sortType);
    }

    @Override
    public String execute() {
        List<ILine> data = getInput().getData();
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

    private List<String> sortByNatural(List<ILine> data) {
        data.sort(Comparator.comparing(ILine::getValue));
        List<String> stringList = new ArrayList<>();
        for (ILine line : data) {
            stringList.add(line.getValue());
        }
        return Collections.singletonList(
                String.format(
                        "Sorted data: %s", String.join(" ", stringList)
                )
        );
    }

    private List<String> sortByCount(List<ILine> data) {
        Map<ILine, Integer> map = new LinkedHashMap<>();
        List<String> resulList = new ArrayList<>();
        for (ILine word : data) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        Map<ILine, Integer> sortedMap = Utils.sortByValue(map);         //new TreeMap<>((o1, o2) -> Integer.compare(map.get(o2), map.get(o1)));

        for (Map.Entry<ILine, Integer> entry : sortedMap.entrySet()) {
            Integer value = entry.getValue();

            int percent = Math.round((float) value / data.size() * 100);
            resulList.add(String.format("%s: %d time(s), %d%%", entry.getKey(), value, percent));
        }
        return resulList;
    }
}

//    @Override
//    public String execute() {
//        int countItems = getInputData()
//                .getList()
//                .size();
//
//        String biggest = getString();
//        int countRepeats = getCountRepeats(biggest);
//        int percent = Math.round((float) countRepeats / countItems * 100);
//
//
//        return String.format("""
//                "Total lines: %d.
//                The longest line:
//                 %s\s
//                (%d time(s), %d%%).""", countItems, biggest, countRepeats, percent);
//    }
//
//    private int getCountRepeats(String biggest) {
//        return getInputData()
//                .getList()
//                .stream()
//                .filter(biggest::equals)
//                .toList()
//                .size();
//
//    }
//
//    private String getString() {
//       return getInputData()
//                .getList()
//                .stream()
//                .max(Comparator.comparingInt(String::length))
//                .orElseThrow(IllegalArgumentException::new);
//
//    }


