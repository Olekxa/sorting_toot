
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

    public void launch() throws CommandException {

        GeneralCommand<?, ? extends Data<?>> command = parseCommand(
                config.getType(),
                config.getSortType(),
                config.getInputFile(),
                config.getOutputFile());
        command.execute();
    }

    private GeneralCommand<?, ? extends Data<?>> parseCommand(DataType type, SortType sortType, File inputFile, File outputFile) {
        return switch (type) {
            case LONG -> new GeneralCommand<>(new LongData(inputFile), sortType, outputFile);
            case LINE -> new GeneralCommand<>(new LineData(inputFile), sortType, outputFile);
            case WORD -> new GeneralCommand<>(new WordData(inputFile), sortType, outputFile);
        };
    }
}
