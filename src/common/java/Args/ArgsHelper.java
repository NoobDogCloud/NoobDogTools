package common.java.Args;

import java.util.HashMap;
import java.util.Set;

public class ArgsHelper {
    public static HashMap<String, Object> dictionary(String[] args) {
        HashMap<String, Object> dictionary = new HashMap<>();
        for (int i = 0; i < args.length; i++) {
            String arg = args[i];
            if (arg.startsWith("-")) {
                if (i + 1 < args.length) {
                    dictionary.put(arg, args[i + 1]);
                }
            }
        }
        return dictionary;
    }

    public static Set<String> keys(String[] args) {
        return dictionary(args).keySet();
    }
}
