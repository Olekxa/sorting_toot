package engine;

import data.Data;
import utils.SortType;
import utils.Utils;

import java.io.File;
import java.util.*;
import java.util.stream.Collectors;

public class GeneralCommand<R, D extends Data<R>> {
    private final D input;
    private final SortType sortType;
    private final File outputFile;

    public GeneralCommand(D input, SortType sortType, File outputFile) {
        this.input = input;
        this.sortType = sortType;
        this.outputFile = outputFile;
    }

    protected String process() {
        List<?> data = input.getData();
        List<String> result;

        if (SortType.BY_COUNT.equals(sortType)) {
            result = sortByCount();
        } else {
            result = sortByNatural();
        }
        List<String> refactored = getInvalidList();
        String printInvalid = String.join("", refactored);
        String total = String.format("Total %s: %d.", input.getTypeName(), data.size());
        String sort = String.join("\n", result);
        return String.format("%s%s\n%s", printInvalid, total, sort);
    }

    protected List<String> sortByCount() {
        Map<R, Integer> map = new LinkedHashMap<>();
        List<String> resulList = new ArrayList<>();
        for (R r : this.input.getData()) {
            map.put(r, map.getOrDefault(r, 0) + 1);
        }
        Map<R, Integer> sortedMap = Utils.sortByValue(map);

        for (Map.Entry<R, Integer> entry : sortedMap.entrySet()) {

            int percent = Math.round((float) entry.getValue() / this.input.getData().size() * 100);
            resulList.add(formatter(entry.getKey(), entry.getValue(), percent));
        }
        return resulList;
    }

    private List<String> sortByNatural() {
        return Collections.singletonList(
                String.format(
                        "Sorted data: %s",
                        this.input.getData()
                                .stream()
                                .map(Object::toString)
                                .collect(Collectors.joining(" "))
                ));
    }

    public String formatter(R r, int number, int percent) {
        return String.format("%s: %d time(s), %d%%", r.toString(), number, percent);
    }

    private List<String> getInvalidList() {
        List<String> invalidMessage = input
                .getInvalid()
                .stream()
                .map(s -> String.format("\"%s\" is not a long. It will be skipped.\n", s))
                .collect(Collectors.toList());
        return Collections
                .singletonList(String.join("", invalidMessage));
    }

    public void execute() {
        if (outputFile == null) {
            System.out.println(process());
        } else {
            Utils.write(outputFile, process());
        }
    }
}
