package common.java.Args;

import java.util.HashMap;
import java.util.Set;

public class ArgsHelper {
    public static HashMap<String, Object> dictionary(String[] args) {
        HashMap<String, Object> dictionary = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            if (arg.startsWith("-")) {
                // String key = arg.substring(1);
                Object value = args[i + 1];
                dictionary.put(arg, value);
            }
        }
        return dictionary;
    }

    public static Set<String> keys(String[] args) {
        return dictionary(args).keySet();
    }
}
