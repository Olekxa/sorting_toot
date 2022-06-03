package engine;

import data.DataType;
import errors.CommandException;
import utils.SortType;
import utils.Utils;

import java.io.File;
import java.util.Map;

public class Config {

    private final static String DATA_TYPE = "-dataType";
    private final static String SORT_TYPE = "-sortingType";
    private final static String WRITE_DATA = "-outputFile";
    private final static String READ_DATA = "-inputFile";

    private final DataType type;
    private final SortType sortType;
    private final File inputFile;
    private final File outputFile;

    public Config(String[] args) {
        var commands = Utils.parseArgs(args);
        validateCommands(commands);
        this.type = parseType(commands);
        this.sortType = parseSort(commands);
        this.inputFile = parseInputFile(commands);
        this.outputFile = parseOutFile(commands);
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

    private void validateCommands(Map<String, String> commands) {
        commands
                .keySet()
                .stream()
                .filter(s -> !(DATA_TYPE.equals(s) || SORT_TYPE.equals(s) || WRITE_DATA.equals(s) || READ_DATA.equals(s)))
                .toList()
                .forEach(s -> {
                    System.out.printf("\"%s\" is not a valid parameter. It will be skipped.%n", s);
                    commands.remove(s);
                });
    }

    private DataType parseType(Map<String, String> commands) {
        String value = commands.get(DATA_TYPE);
        DataType dataType = DataType.getDataType(value);
        if (dataType == null) {
            throw new CommandException("No data type defined!");
        }
        return dataType;
    }

    private SortType parseSort(Map<String, String> commands) {
        String value = commands.get(SORT_TYPE);
        SortType type = SortType.getType(value);
        if (type == null) {
            throw new CommandException("No sorting type defined!");
        }
        return type;
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
