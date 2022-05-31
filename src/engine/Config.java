package engine;

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

    private final static String DATA_TYPE = "-dataType";
    private final static String SORT_TYPE = "-sortingType";
    private final static String WRITE_DATA = "-outputFile";
    private final static String READ_DATA = "-inputFile";

    private DataType type;
    private SortType sortType;
    private File inputFile;
    private File outputFile;

    public Config(String[] args) {
        try {
            init(Utils.parseArgs(args));
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

    private void init(Map<String, String> commands) {
        try {
            validateCommands(commands);
        } catch (UnknownCommandException e) {
            e.getMessages().forEach(System.out::println);
            e.getErrors().forEach(commands::remove);
            init(commands);
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
                .filter(s -> !(DATA_TYPE.equals(s) || SORT_TYPE.equals(s) || WRITE_DATA.equals(s) || READ_DATA.equals(s)))
                .collect(Collectors.toList());
        if (!unknownCommands.isEmpty()) {
            throw new UnknownCommandException(unknownCommands);
        }
    }

    private DataType parseType(Map<String, String> commands) {
        String value = commands.get(DATA_TYPE);
        return DataType.getDataType(value);
    }

    private SortType parseSort(Map<String, String> commands) {
        String value = commands.get(SORT_TYPE);
        return SortType.getType(value);
    }

    private File parseOutFile(Map<String, String> commands) {
        return getFile(commands, WRITE_DATA);
    }

    private File parseInputFile(Map<String, String> commands) {
        return getFile(commands, READ_DATA);
    }

    private File getFile(Map<String, String> commands, String key) {
        String value = commands.get(key);
        if (value != null && !value.isEmpty()) {
            return new File(value);
        }
        return null;
    }
}
