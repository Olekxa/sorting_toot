
import data.*;
import engine.Command;
import engine.ParseLineCommand;
import engine.ParseLongCommand;
import engine.ParseWordCommand;
import errors.*;
import utils.SortType;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


public class Tool {
    private final Map<String, String> commands;

    public Tool(String[] args) {
        commands = parseArgs(args);
    }

    public void run() {
        try {
            Command<? extends Data<?>> command = factory(commands);
            String result = command.execute();
            if (commands.containsKey(Commands.WRITE_DATA)) {
                writeData(commands, result);
            } else {
                System.out.println(result);
            }
        } catch (UnknownCommandException e) {
            e.getMessages().forEach(System.out::println);
            e.getErrors().forEach(commands::remove);
            run();
        } catch (CommandException e) {
            e.getErrors().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private Command factory(Map<String, String> commands) throws FileCommandException,
            NoDataException,
            NoSortException,
            UnknownCommandException {
        unknownCommand(commands);
        SortType sortType = parseSort(commands);
        File inputFile = readInputFile(commands);
        String value = commands.get(Commands.DATA_TYPE);
        DataType type = DataType.getDataType(value);
        if (DataType.LONG == type) {
            return new ParseLongCommand(new LongData(inputFile), sortType);
        } else if (DataType.LINE == type) {
            return new ParseLineCommand(new LineData(inputFile), sortType);
        } else if (DataType.WORD == type) {
            return new ParseWordCommand(new WordData(inputFile), sortType);
        } else {
            throw new NoDataException();
        }
    }

    private void writeData(Map<String, String> commands, String data) {
        String value = commands.get(Commands.WRITE_DATA);
        File file = new File(value);
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(data);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    private File readInputFile(Map<String, String> commands) {
        String value = commands.get(Commands.READ_DATA);
        if (value != null && !value.isBlank()) {
            return new File(value);
        }
        return null;
    }

    private void unknownCommand(Map<String, String> commands) {
        List<String> unknownCommands = commands
                .keySet()
                .stream()
                .filter(s -> !(Commands.DATA_TYPE.equals(s)
                        || Commands.SORT_TYPE.equals(s)
                        || Commands.WRITE_DATA.equals(s)
                        || Commands.READ_DATA.equals(s))).toList();
        if (!unknownCommands.isEmpty()) {
            throw new UnknownCommandException(unknownCommands);
        }
    }


    private SortType parseSort(Map<String, String> commands) throws NoSortException {
        if (commands.containsKey(Commands.SORT_TYPE)) {
            String value = commands.get(Commands.SORT_TYPE);
            SortType type = SortType.getType(value);
            if (type == null) {
                throw new NoSortException();
            } else {
                return type;
            }
        } else return SortType.NONE;
    }

    private Map<String, String> parseArgs(String[] args) {
        Map<String, String> params = new HashMap<>();
        String arg;
        String val;
        for (int i = 0; i < args.length; i++) {
            if (args[i].matches("-.+")) {
                arg = args[i];
                if (i == args.length - 1) {
                    val = "";
                } else if (args[i + 1].matches("-.+")) {
                    val = "";
                } else {
                    val = args[i + 1];
                }
                params.put(arg, val);
            }
        }
        return params;
    }
}
