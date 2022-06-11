package engine;

import data.Data;
import utils.SortType;
import utils.Utils;
import java.io.File;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
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

    private String process() {
        List<?> data = input.getData();
        List<String> result;

        if (SortType.BY_COUNT.equals(sortType)) {
            result = sortByCount();
        } else {
            result = sortByNatural();
        }

        String total = String.format("Total %s: %d.", input.getTypeName(), data.size());
        String sort = String.join("\n", result);
        return String.format("%s\n%s", total, sort);
    }

    private List<String> sortByCount() {
        Map<R, Integer> map = new HashMap<>();
        for (R r : input.getData()) {
            map.put(r, map.getOrDefault(r, 0) + 1);
        }
        return Utils.sortByValue(map)
                .entrySet()
                .stream()
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

    public void execute() {
        if (outputFile == null) {
            System.out.println(process());
        } else {
            Utils.write(outputFile, process());
        }
    }
}
