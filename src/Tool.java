import data.Data;
import data.DataType;
import data.LineData;
import data.LongData;
import data.WordData;
import engine.Config;
import engine.GeneralCommand;
import errors.CommandException;

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
