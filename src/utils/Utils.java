package utils;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

public class Utils {
    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(Map.Entry.comparingByValue());

        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    public static Map<String, String> parseArgs(String[] args) {
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

    public static void write(File file, String data) {
        try (FileWriter fileWriter = new FileWriter(file)) {
            fileWriter.write(data);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
