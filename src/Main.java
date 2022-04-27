import engine.Sorting;

import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Sorting sorting = new Sorting(scanner);
        List<String> list = sorting.readInput();
        System.out.println(sorting.getBiggestInt(list));
    }
}
