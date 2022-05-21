package engine;

import data.*;
import errors.*;
import utils.SortType;
import utils.Utils;

import java.io.File;
import java.util.List;
import java.util.Map;


public class Config {
    private SortType sortType;
    private File inputFile;
    private DataType type;
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

    private void factory(
            Map<String, String> commands
    ) throws FileCommandException, NoDataException, NoSortException {
        try {
            unknownCommand(commands);
        } catch (UnknownCommandException e) {
            e.getMessages().forEach(System.out::println);
            for (String error : e.getErrors()) {
                commands.remove(error);
            }
            factory(commands);
        }
        this.sortType = parseSort(commands);
        this.inputFile = readInputFile(commands);
        String value = commands.get(Commands.DATA_TYPE);
        this.type = DataType.getDataType(value);
        this.outputFile = parseOutFile(commands);

    }

    private File parseOutFile(Map<String, String> commands) {
        String value = commands.get(Commands.WRITE_DATA);
        if (value != null && !value.isBlank()) {
            return new File(value);
        }
        return null;
    }

    private File readInputFile(Map<String, String> commands) throws FileCommandException {
        String value = commands.get(Commands.READ_DATA);
        if (value != null && !value.isBlank()) {
            File file = new File(value);
            if (file.exists() && file.canWrite() && file.isFile()) {
                return file;
            } else {
                throw new FileCommandException();
            }
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
        } else throw new NoSortException();
    }

    public SortType getSortType() {
        return sortType;
    }

    public File getInputFile() {
        return inputFile;
    }

    public DataType getType() {
        return type;
    }

    public File getOutputFile() {
        return outputFile;
    }
}
