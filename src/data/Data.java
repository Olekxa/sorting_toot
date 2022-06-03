package data;

import errors.CommandException;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public abstract class Data<T> {
    private final List<T> data;
    private final List<String> invalid;


    public Data(File input) throws CommandException {
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

    private Scanner getScanner(File input) throws CommandException {
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
