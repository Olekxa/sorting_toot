
import data.*;
import engine.*;
import errors.*;
import utils.SortType;

import java.io.File;

public class Tool {
    private final Config config;

    public Tool(Config cfg) {
        this.config = cfg;
    }

    public void launch() {
        try {
            Command<?, ? extends Data<?>> command = parseCommand(config.getInputFile(), config.getSortType(), config.getType(), config.getOutputFile());
            command.execute();
        } catch (NoDataException e) {
            e.getErrors().forEach(System.out::println);
        }
    }

    private Command<?, ? extends Data<?>> parseCommand(File file, SortType sortType, DataType type, File outputFile) {
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
}
