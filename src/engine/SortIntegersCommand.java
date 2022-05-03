//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package engine;

import data.InputData;

import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

public class SortIntegersCommand extends Command {
    public SortIntegersCommand(InputData inputData) {
        super(inputData);
        this.getInputData().readInput();
    }

    public String execute() {
        List<Integer> innStream = this.getList();
        int countItems = innStream.size();
        StringBuilder listAsString = new StringBuilder();

        for (Integer integer : innStream) {
            listAsString.append(" ").append(integer.toString());
        }

        return String.format("Total numbers: %d.\nSorted data: %s", countItems, listAsString.toString().trim());
    }

    private List<Integer> getList() {
        return this.getInputData()
                .getList()
                .stream()
                .map(String::trim)
                .flatMap((s) -> Arrays.stream(s.split("[a-zA-Z]+|\\s")))
                .filter((x) -> x.matches("-?\\d+(\\.\\d+)?"))
                .map(Integer::parseInt)
                .sorted()
                .toList();
    }
}
