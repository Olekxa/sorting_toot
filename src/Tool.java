
import data.*;
import engine.*;
import errors.*;

import java.io.File;

public class Tool {
    private final Config config;

    public Tool(Config cfg) {
        this.config = cfg;
    }

    public void launch() throws CommandException {
        GeneralCommand<?, ? extends Data<?>> command = new GeneralCommand<>(
                parseData(config.getType(), config.getInputFile()),
                config.getSortType(),
                config.getOutputFile());
        command.execute();
    }

    private Data<?> parseData(DataType type, File inputFile) {
        return switch (type) {
            case LONG -> new LongData(inputFile);
            case LINE -> new LineData(inputFile);
            case WORD -> new WordData(inputFile);
        };
    }
}
