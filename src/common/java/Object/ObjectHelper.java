package common.java.Object;

import common.java.String.StringHelper;
import org.json.gsc.JSONArray;
import org.json.gsc.JSONObject;

public class ObjectHelper {
    public static Object build(Object o) {
        Object r;
        String v = StringHelper.toString(o);
        // double
        try {
            r = Double.parseDouble(v);
        } catch (Exception e) {
            r = null;
        }

        // float
        if (r == null) {
            try {
                r = Float.parseFloat(v);
            } catch (Exception e) {
                r = null;
            }
        }
        // int
        if (r == null) {
            try {
                r = Integer.parseInt(v);
            } catch (Exception e) {
                r = null;
            }
        }
        // long
        if (r == null) {
            try {
                r = Long.parseLong(v);
            } catch (Exception e) {
                r = null;
            }
        }
        // jsonObject
        if (r == null) {
            r = JSONObject.build(v);
        }
        // jsonArray
        if (r == null) {
            r = JSONArray.build(v);
        }
        // string
        if (r == null) {
            return v;
        }
        return r;
    }
}
