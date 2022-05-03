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
        if (args[1].equals(Constants.SORT_LINE)) {
            return new ParseLineCommand(inputData);
        } else if (args[1].equals(Constants.SORT_LONG)) {
            return new ParseLongCommand(inputData);
        } else if (args[1].equals(Constants.SORT_WORD)) {
            return new ParseWordCommand(inputData);
        } else {
            return new ParseUnknownCommand(inputData);
        }
    }
}