import data.Constants;
import data.InputData;
import engine.*;

import java.util.Scanner;


public class SortingTool {
    public static void main(String[] args) {
        try (Scanner scanner = new Scanner(System.in)) {
            InputData inputData = new InputData(scanner);
            Command command = (runApp(args, inputData));
            System.out.println(command.execute());
        }
    }

    public static Command runApp(String[] args, InputData inputData) {
        if (args.length==1 && args[0].equals(Constants.SORT_INT_BY_ORDER)){
            return new SortIntegersCommand(inputData);
        }
        if (args.length != 2 || !args[0].equals(Constants.BASE)) {
            throw new IllegalArgumentException("Wrong command");
        }
        return switch (args[1]) {
            case Constants.SORT_LINE -> new ParseLineCommand(inputData);
            case Constants.SORT_LONG -> new ParseLongCommand(inputData);
            case Constants.SORT_WORD -> new ParseWordCommand(inputData);
            default -> new ParseUnknownCommand(inputData);
        };
    }
}