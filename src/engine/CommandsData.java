package engine;

import java.util.HashMap;
import java.util.Map;

public class CommandsData {
    private final Map<String, String> commands;

    public CommandsData(String[] args) {
        this.commands = parseArgs(args);
    }

    public Map<String, String> getList() {
        return commands;
    }

    public void remove(String key) {
        commands.remove(key);
    }

    public boolean contains(String key) {
        return commands.containsKey(key);
    }

    private Map<String, String> parseArgs(String[] args) {
        Map<String, String> params = new HashMap<>();
        String arg;
        String val;
        for (int i = 0; i < args.length; i++) {
            if (args[i].matches("-.+")) {
                arg = args[i];
                if (i == args.length - 1) {
                    val = "";
                } else if (args[i + 1].matches("-.+")) {
                    val = "";
                } else {
                    val = args[i + 1];
                }
                params.put(arg, val);
            }
        }
        return params;
    }
}
