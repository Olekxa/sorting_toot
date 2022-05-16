package engine;


import data.WordData;
import utils.SortType;
import utils.Utils;

import java.util.*;

public class ParseWordCommand extends Command<WordData> {

    public ParseWordCommand(WordData input, SortType sortType) {
        super(input, sortType);
    }

    @Override
    public String execute() {
        List<String> data = getInput().getData();
        List<String> result;
        if (SortType.BY_COUNT.equals(getSortType())) {
            result = sortByCount(data);
        } else {
            result = sortByNatural(data);
        }
        String total = String.format("Total words: %d.", data.size());
        String sort = String.join("\n", result);
        return String.format("%s\n%s", total, sort);
    }

    private List<String> sortByNatural(List<String> data) {
        return Collections.singletonList(
                String.format(
                        "Sorted data: %s", String.join(" ", data)
                )
        );
    }

    private List<String> sortByCount(List<String> words) {
        Map<String, Integer> map = new LinkedHashMap<>();
        List<String> resulList = new ArrayList<>();
        for (String word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        Map<String, Integer> sortedMap = Utils.sortByValue(map);

        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            Integer value = entry.getValue();

            int percent = Math.round((float) value / words.size() * 100);
            resulList.add(String.format("%s: %d time(s), %d%%", entry.getKey(), value, percent));
        }
        return resulList;
    }
}