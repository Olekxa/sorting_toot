package engine;


import data.WordData;
import item.IWord;
import utils.SortType;
import utils.Utils;

import java.util.*;

public class ParseWordCommand extends Command<WordData> {

    public ParseWordCommand(WordData input, SortType sortType) {
        super(input, sortType);
    }

    @Override
    public String execute() {
        List<IWord> data = getInput().getData();
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

    private List<String> sortByNatural(List<IWord> data) {
        data.sort(Comparator.comparing(IWord::getValue));
        List<String> stringList = new ArrayList<>();
        for (IWord line : data) {
            stringList.add(line.getValue());
        }
        return Collections.singletonList(
                String.format(
                        "Sorted data: %s", String.join(" ", stringList)
                )
        );
    }

    private List<String> sortByCount(List<IWord> words) {
        Map<IWord, Integer> map = new LinkedHashMap<>();
        List<String> resulList = new ArrayList<>();
        for (IWord word : words) {
            map.put(word, map.getOrDefault(word, 0) + 1);
        }

        Map<IWord, Integer> sortedMap = Utils.sortByValue(map);         //new TreeMap<>((o1, o2) -> Integer.compare(map.get(o2), map.get(o1)));

        for (Map.Entry<IWord, Integer> entry : sortedMap.entrySet()) {
            Integer value = entry.getValue();

            int percent = Math.round((float) value / words.size() * 100);
            resulList.add(String.format("%s: %d time(s), %d%%", entry.getKey(), value, percent));
        }
        return resulList;
    }
}