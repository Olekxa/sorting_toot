package engine;

import data.Data;
import utils.SortType;
import utils.Utils;


import javax.swing.*;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public abstract class Command<T, E extends Data<T>> {
    private final E input;
    private final SortType sortType;
    private final File outputFile;

    public Command(E input, SortType sortType, File outputFile) {
        this.input = input;
        this.sortType = sortType;
        this.outputFile = outputFile;
    }

    public abstract String process();

    protected E getInput() {
        return input;
    }

    protected SortType getSortType() {
        return sortType;
    }

    protected List<String> sortByCount(List<T> longs) {
        Map<T, Integer> map = new LinkedHashMap<>();
        List<String> resulList = new ArrayList<>();
        for (T iLong : longs) {
            map.put(iLong, map.getOrDefault(iLong, 0) + 1);
        }
        Map<T, Integer> sortedMap = Utils.sortByValue(map);

        for (Map.Entry<T, Integer> entry : sortedMap.entrySet()) {

            int percent = Math.round((float) entry.getValue() / longs.size() * 100);
            resulList.add(formatter(entry.getKey(), entry.getValue(), percent));
        }
        return resulList;
    }

    abstract public String formatter(T t, int number, int percent);

    public void execute() {
        if (outputFile == null) {
            System.out.println(process());
        } else {
            Utils.write(outputFile, process());
        }
    }
}