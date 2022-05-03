package data;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class InputData {
    private List<String> list;
    private Scanner scanner;

    public InputData(Scanner scanner) {
        this.list = new ArrayList<>();
        this.scanner = scanner;
    }

    public void readInput() {
        while (scanner.hasNext()) {
            list.add(scanner.nextLine());
        }
    }

    public List<String> getList() {
        return list;
    }
}
