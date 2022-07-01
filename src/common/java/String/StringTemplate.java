package common.java.String;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class StringTemplate {
    private final String[] ContentArr;
    private final HashMap<String, Integer> PlaceholderResolver = new HashMap<>();

    public StringTemplate(String Str) {
        List<String> tempContentArr = new ArrayList<>();
        // 解析 str
        char[] charArr = Str.toCharArray();
        int lastIdx = 0;
        int startIdx = -1;
        int endId;
        for (int i = 0; i < charArr.length; i++) {
            if (charArr[i] == '$' && (i + 1) < charArr.length && charArr[i + 1] == '{') {
                startIdx = i;
                i++;
                continue;
            }
            if (charArr[i] == '}' && startIdx >= 0) {
                endId = i;
                if (startIdx > lastIdx) {
                    tempContentArr.add(Str.substring(lastIdx, startIdx));
                }
                if (endId > startIdx + 2) {
                    String keyName = Str.substring(startIdx + 2, endId);
                    tempContentArr.add(keyName);
                    PlaceholderResolver.put(keyName, tempContentArr.size() - 1);
                }
                lastIdx = endId + 1;
                startIdx = -1;
            }
        }
        if (Str.length() > 2) {
            if (startIdx >= 0) {
                tempContentArr.add(Str.substring(startIdx + 2));
            } else {
                tempContentArr.add(Str.substring(lastIdx));
            }
        }

        ContentArr = tempContentArr.toArray(new String[0]);
    }

    /**
     * 替换模板内容
     */
    public StringTemplate replace(String key, String val) {
        if (PlaceholderResolver.containsKey(key)) {
            int idx = PlaceholderResolver.get(key);
            ContentArr[idx] = val;
        }
        return this;
    }

    /**
     * 替换模板内容
     */
    public StringTemplate replace(HashMap<String, Object> list) {
        for (String key : list.keySet()) {
            replace(key, StringHelper.toString(list.get(key)));
        }
        return this;
    }

    public String toString() {
        return StringHelper.join(ContentArr, "");
    }
}
