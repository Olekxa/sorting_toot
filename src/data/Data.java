package data;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Data<T> {
    private final List<T> data;
    private final List<String> invalid;

    public Data(File input) throws FileNotFoundException {
        List<String> data = new ArrayList<>();
        try (Scanner scanner = getScanner(input)) {
            while (scanner.hasNext()) {
                data.add(scanner.nextLine());
            }
        }
        this.data = mapData(data);
        this.invalid = mapInvalid(data);
    }

    public List<String> mapInvalid(List<String> data) {
        return new ArrayList<>();
    }

    public abstract List<T> mapData(List<String> data);

    public List<T> getData() {
        return data;
    }

    public List<String> getInvalid() {
        return invalid;
    }

    private Scanner getScanner(File input) throws FileNotFoundException {
        if (input != null) {
            return new Scanner(input);
        } else {
            return new Scanner(System.in);
        }
    }

}
