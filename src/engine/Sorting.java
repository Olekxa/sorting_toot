package engine;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Sorting {
    Scanner scanner;
    List<String> listOfSymbols;
    Pattern digit = Pattern.compile("-?\\d+(\\.\\d+)?");

    public Sorting(Scanner scanner) {
        this.scanner = scanner;
    }

    public List<String> readInput() {
        return listOfSymbols = Arrays
                .stream(scanner
                        .nextLine()
                        .trim()
                        .split(" "))
                .sequential()
                .collect(Collectors.toList());
    }

    public int getBiggestInt(List<String> list) {
        return list
                .stream()
                .filter(x -> digit.matcher(x).matches())
                .mapToInt(Integer::parseInt)
                .max()
                .orElseThrow(NoSuchElementException::new);
    }
}
