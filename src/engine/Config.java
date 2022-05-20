package engine;

import data.*;
import errors.*;
import utils.SortType;
import utils.Utils;

import java.io.File;
import java.util.List;
import java.util.Map;


public class Config {
    private Command<?, ? extends Data<?>> command;


    public Config(String[] args) {
        try {
            this.command = factory(Utils.parseArgs(args));
        } catch (CommandException e) {
            System.out.println(e.getErrors());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void execute() {
        command.execute();
    }

    private Command<?, ? extends Data<?>> factory(
            Map<String, String> commands
    ) throws FileCommandException, NoDataException, NoSortException {
        try {
            unknownCommand(commands);
        } catch (UnknownCommandException e) {
            e.getMessages().forEach(System.out::println);
            for (String error : e.getErrors()) {
                commands.remove(error);
            }
            return factory(commands);
        }
        SortType sortType = parseSort(commands);
        File inputFile = readInputFile(commands);
        String value = commands.get(Commands.DATA_TYPE);
        DataType type = DataType.getDataType(value);
        File outputFile = parseOutFile(commands);
        return parseCommand(inputFile, sortType, type, outputFile);
    }

    private Command<?, ? extends Data<?>> parseCommand(File file, SortType sortType, DataType type, File outputFile) throws FileCommandException {
        if (DataType.LONG == type) {
            return new ParseLongCommand(new LongData(file), sortType, outputFile);
        } else if (DataType.LINE == type) {
            return new ParseLineCommand(new LineData(file), sortType, outputFile);
        } else if (DataType.WORD == type) {
            return new ParseWordCommand(new WordData(file), sortType, outputFile);
        } else {
            throw new NoDataException();
        }
    }

    private File parseOutFile(Map<String, String> commands) {
        String value = commands.get(Commands.WRITE_DATA);
        if (value != null && !value.isBlank()) {
            return new File(value);
        }
        return null;
    }

    private File readInputFile(Map<String, String> commands) {
        String value = commands.get(Commands.READ_DATA);
        if (value != null && !value.isBlank()) {
            return new File(value);
        }
        return null;
    }

    private void unknownCommand(Map<String, String> commands) {
        List<String> unknownCommands = commands.keySet().stream().filter(s -> !(Commands.DATA_TYPE.equals(s) || Commands.SORT_TYPE.equals(s) || Commands.WRITE_DATA.equals(s) || Commands.READ_DATA.equals(s))).toList();
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
}
