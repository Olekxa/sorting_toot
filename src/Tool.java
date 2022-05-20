
import data.*;
import engine.*;
import errors.*;

public class Tool {
    private final Config config;

    public Tool(Config cfg) {
        this.config = cfg;
    }

    public void launch() {
        config.execute();
    }
}
