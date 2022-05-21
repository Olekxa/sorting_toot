package engine;

import data.Commands;
import data.DataType;
import errors.CommandException;
import errors.NoDataException;
import errors.NoSortException;
import errors.UnknownCommandException;
import utils.SortType;
import utils.Utils;

import java.io.File;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;


public class Config {

    private DataType type;
    private SortType sortType;
    private File inputFile;
    private File outputFile;

    public Config(String[] args) {
        try {
            factory(Utils.parseArgs(args));
        } catch (CommandException e) {
            e.getErrors().forEach(System.out::println);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public SortType getSortType() {
        if (sortType == null) {
            throw new NoSortException();
        } else {
            return sortType;
        }
    }

    public DataType getType() {
        if (type == null) {
            throw new NoDataException();
        } else {
            return type;
        }
    }

    public File getInputFile() {
        return inputFile;
    }

    public File getOutputFile() {
        return outputFile;
    }

    private void factory(Map<String, String> commands) {
        try {
            validateCommands(commands);
        } catch (UnknownCommandException e) {
            e.getMessages().forEach(System.out::println);
            e.getErrors().forEach(commands::remove);
            factory(commands);
        }

        this.type = parseType(commands);
        this.sortType = parseSort(commands);
        this.inputFile = parseInputFile(commands);
        this.outputFile = parseOutFile(commands);

    }

    private void validateCommands(Map<String, String> commands) throws UnknownCommandException {
        List<String> unknownCommands = commands
                .keySet()
                .stream()
                .filter(s -> !(Commands.DATA_TYPE.equals(s) || Commands.SORT_TYPE.equals(s) || Commands.WRITE_DATA.equals(s) || Commands.READ_DATA.equals(s)))
                .collect(Collectors.toList());
        if (!unknownCommands.isEmpty()) {
            throw new UnknownCommandException(unknownCommands);
        }
    }

    private DataType parseType(Map<String, String> commands) {
        String value = commands.get(Commands.DATA_TYPE);
        return DataType.getDataType(value);
    }

    private SortType parseSort(Map<String, String> commands) {
        String value = commands.get(Commands.SORT_TYPE);
        return SortType.getType(value);
    }

    private File parseOutFile(Map<String, String> commands) {
        return getFile(commands, Commands.WRITE_DATA);
    }

    private File parseInputFile(Map<String, String> commands) {
        return getFile(commands, Commands.READ_DATA);
    }

    private File getFile(Map<String, String> commands, String key) {
        String value = commands.get(key);
        if (value != null && !value.isEmpty()) {
            return new File(value);
        }
        return null;
    }
}
