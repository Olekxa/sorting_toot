import engine.Config;
import errors.CommandException;

public class SortingTool {
    public static void main(String[] args) {
        try {
            Config config = new Config(args);
            Tool tools = new Tool(config);
            tools.launch();
        } catch (CommandException e) {
            System.out.println(e.getError());
        } catch (Exception e) {
            System.out.println("something went wrong");
        }
    }
}
