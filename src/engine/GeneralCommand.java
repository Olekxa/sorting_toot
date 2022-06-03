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

    public String process() {
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

    private List<String> sortByCount() {
        Map<R, Integer> map = new HashMap<>();
        for (R r : input.getData()) {
            map.put(r, map.getOrDefault(r, 0) + 1);
        }
        return Utils.sortByValue(map).entrySet().stream()
                .map(entry -> {
                    int percent = Math.round((float) entry.getValue() / input.getData().size() * 100);
                    return String.format("%s: %d time(s), %d%%", entry.getKey(), entry.getValue(), percent);
                }).collect(Collectors.toList());
    }

    private List<String> sortByNatural() {
        String collect = this.input.getData()
                .stream()
                .map(Object::toString)
                .collect(Collectors.joining(" "));

        return Collections.singletonList(
                String.format("Sorted data: %s", collect));
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
