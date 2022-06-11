package data;

import errors.CommandException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Data<T> {
    private final List<T> data;

    public Data(File input) {
        List<String> data = new ArrayList<>();
        try (Scanner scanner = getScanner(input)) {
            while (scanner.hasNext()) {
                data.add(scanner.nextLine());
            }
        }
        this.data = mapData(data);
    }

    public abstract List<T> mapData(List<String> data);

    public List<T> getData() {
        return data;
    }

    private Scanner getScanner(File input) {
        if (input != null) {
            try {
                return new Scanner(input);
            } catch (FileNotFoundException e) {
                throw new CommandException("File not found");
            }
        } else {
            return new Scanner(System.in);
        }
    }

    public abstract String getTypeName();

}
