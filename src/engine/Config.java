package engine;

import data.DataType;
import errors.CommandException;
import utils.SortType;
import utils.Utils;

import java.io.File;
import java.util.List;
import java.util.Map;

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
        init(Utils.parseArgs(args));
    }

    public SortType getSortType() {
        return sortType;
    }

    public DataType getType() {
        return type;
    }

    public File getInputFile() {
        return inputFile;
    }

    public File getOutputFile() {
        return outputFile;
    }

    private void init(Map<String, String> commands) {

        validateCommands(commands);

        this.type = parseType(commands);
        this.sortType = parseSort(commands);
        this.inputFile = parseInputFile(commands);
        this.outputFile = parseOutFile(commands);
    }

    private void validateCommands(Map<String, String> commands) /* throws UnknownCommandException*/ {
        if (type == null) {
            throw new CommandException("No data type defined!");
        }
        if (sortType == null) {
            throw new CommandException("No sorting type defined!");
        }
        List<String> unknownCommands = commands
                .keySet()
                .stream()
                .filter(s -> !(DATA_TYPE.equals(s) || SORT_TYPE.equals(s) || WRITE_DATA.equals(s) || READ_DATA.equals(s))).toList();
        if (!unknownCommands.isEmpty()) {
            unknownCommands.stream()
                    .map(s -> String.format("\"%s\" is not a valid parameter. It will be skipped.", s))
                    .forEach(System.out::println);
            unknownCommands.forEach(commands::remove);
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
