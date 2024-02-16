package common.java.Encrypt;

import org.json.gsc.JSONArray;
import org.json.gsc.JSONObject;

public class GscEncrypt {

    /**
     * 对html编码解密
     *
     * @param html
     * @return
     */
    private static String _decodeHtmlTag(String html) {
        return html.replaceAll("@t", "/").replaceAll("@w", "+");
    }

    private static String _encodeHtmlTag(String html) {
        return html.replaceAll("/", "@t").replaceAll("\\+", "@w");
    }

    /**
     * 转码json->加密
     *
     * @param json
     * @return
     */
    public static String encodeJson(JSONObject json) {
        return "gsc-json&" + _encodeString(json.toString());
    }

    public static String encodeJsonArray(JSONArray<?> json) {
        return "gsc-jsonArray&" + _encodeString(json.toString());
    }

    public static String encodeString(String str) {
        return "gsc-string&" + _encodeString(str);
    }

    private static String _encodeString(String str) {
        // add header info
        return _encodeHtmlTag(Base64.encode(str));
    }

    /**
     * 转码json->解密
     *
     * @param jsonString
     * @return
     */
    public static JSONObject decodeJson(String jsonString) {
        var header = getHeader(jsonString);
        if (header == null || !getType(header).equals("json")) {
            return null;
        }
        return JSONObject.toJSON(_decodeString(jsonString.substring(header.length() + 1)));
    }

    public static JSONArray<?> decodeJsonArray(String jsonArrayString) {
        var header = getHeader(jsonArrayString);
        if (header != null && !getType(header).equals("jsonArray")) {
            return null;
        }
        return JSONArray.toJSONArray(_decodeString(jsonArrayString.substring(header.length() + 1)));
    }

    public static String decodeString(String str) {
        var header = getHeader(str);
        if (header == null || !getType(header).equals("string")) {
            return null;
        }
        return _decodeString(str.substring(header.length() + 1));
    }

    public static Object decode(String str) {
        var header = getHeader(str);
        if (header == null) {
            return null;
        }
        return switch (getType(header)) {
            case "json" -> decodeJson(str);
            case "jsonArray" -> decodeJsonArray(str);
            case "string" -> decodeString(str);
            default -> null;
        };
    }

    private static String _decodeString(String str) {
        return Base64.decode(_decodeHtmlTag(str));
    }

    public static String getHeader(String str) {
        var header = str.split("&")[0];
        return header.startsWith("gsc-") ? header : null;
    }

    public static String getType(String header) {
        if (header == null) {
            return null;
        }
        return header.split("-")[1];
    }
}
