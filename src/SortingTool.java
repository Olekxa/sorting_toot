import engine.Config;

public class SortingTool {
    public static void main(String[] args) {
        Config config = new Config(args);
        Tool tools = new Tool(config);

        tools.launch();
    }
}
