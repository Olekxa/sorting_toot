

public class SortingTool {
    public static void main(String[] args) {
        Tool tools = new Tool(args);
        try {
            tools.launch();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
