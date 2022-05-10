

import java.io.FileNotFoundException;


public class SortingTool {
    public static void main(String[] args) {
        Tool tools = new Tool(args);
        try {
            tools.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
