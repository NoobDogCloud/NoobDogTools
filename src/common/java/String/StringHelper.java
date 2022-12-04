package common.java.String;

import common.java.Random.Random;
import org.json.gsc.JSONArray;
import org.json.gsc.JSONObject;

import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class StringHelper {
    private final static Pattern pattern = Pattern.compile("\\s*|\t|\r|\n");

    private String str;

    private StringHelper(String str) {
        this.str = str;
    }

    public static StringHelper build(String str) {
        return new StringHelper(str);
    }

    public static String join(Set<?> ary) {
        return join(ary, ",");
    }

    public static String join(Set<?> ary, Function<Object, Object> func) {
        return join(ary, ",", func);
    }

    public static String join(Set<?> ary, String ichar) {
        return join(ary, ichar, null);
    }

    public static String join(Set<?> ary, String ichar, Function<Object, Object> func) {
        StringBuilder r = new StringBuilder();
        for (Object v : ary) {
            Object vv = v;
            if (func != null) {
                vv = func.apply(v);
            }
            r.append(vv).append(ichar);
        }
        return StringHelper.trimFrom(r.toString(), ichar.charAt(0));
    }

    /**
     * 字符串按,指定的字符拼接
     *
     * @param ary
     * @return
     */
    public static String join(List<?> ary) {
        return join(ary, ",");
    }

    public static String join(List<?> ary, Function<Object, Object> func) {
        return join(ary, ",", func);
    }

    public static String join(List<?> ary, String ichar) {
        return join(ary, ichar, null);
    }

    public static String join(List<?> ary, String ichar, Function<Object, Object> func) {
        StringBuilder r = new StringBuilder();
        for (Object v : ary) {
            Object vv = v;
            if (func != null) {
                vv = func.apply(v);
            }
            r.append(vv).append(ichar);
        }
        return StringHelper.trimFrom(r.toString(), ichar.charAt(0));
    }

    public static String join(String[] strary) {
        return join(strary, ",", 0, -1, null);
    }

    public static String join(String[] strary, Function<String, String> func) {
        return join(strary, ",", 0, -1, func);
    }

    public static String join(String[] strary, String ichar) {
        return join(strary, ichar, 0, -1, null);
    }

    public static String join(String[] strary, String ichar, Function<String, String> func) {
        return join(strary, ichar, 0, -1, func);
    }

    public static String join(String[] strary, String ichar, int idx) {
        return join(strary, ichar, 0, idx, null);
    }

    public static String join(String[] strary, String ichar, int idx, Function<String, String> func) {
        return join(strary, ichar, 0, idx, func);
    }

    public static String join(String[] strary, String ichar, int start, int idx) {
        return join(strary, ichar, start, idx, null);
    }

    /**
     * 字符串数组合并成字符串
     *
     * @param strary 字符串数组
     * @param ichar  字符串连接字符
     * @param start  合并开始索引
     * @param idx    合并执行长度,-1标识直接到结尾
     * @return
     */
    public static String join(String[] strary, String ichar, int start, int idx, Function<String, String> func) {
        if (idx == -1) {
            idx = strary.length;
        }
        if (idx == 0) {
            return "";
        }
        int len = idx + start;
        if (len > strary.length) {
            len = strary.length;
        }
        StringBuilder rs = new StringBuilder();
        for (int i = start; i < len; i++) {
            String v = strary[i];
            if (func != null) {
                v = func.apply(v);
            }
            rs.append(v).append(ichar);
        }
        String v = rs.toString();
        return StringHelper.build(v).removeTrailingFrom(ichar.length()).toString();
    }

    /**
     * 获得指定长度字符串
     */
    public static String createRandomCode(int length) {
        String str = "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; ++i) {
            int number = Random.next(62);// [0,62)
            sb.append(str.charAt(number));
        }
        return sb.toString();
    }

    /**
     * 根据传入变量类型生成对应的字符串表达式
     *
     * @param obj
     * @return
     */
    public static String typeString(Object obj) {
        return typeString(obj, "\"");
    }

    /**
     * 根据传入变量类型生成对应的字符串表达式
     *
     * @param obj
     * @return
     */
    public static String typeString(Object obj, String replace_char) {
        String rString;
        String r2;
        if (obj != null) {
            r2 = obj.toString();
            if (obj instanceof String) {
                rString = replace_char + obj + replace_char;
            } else if (obj instanceof JSONObject) {
                rString = replace_char + r2 + replace_char;
            } else if (obj instanceof JSONArray) {
                rString = replace_char + r2 + replace_char;
            } else {
                rString = r2;
            }
        } else {
            rString = "null";
        }
        return rString;
    }

    public static String trimFrom(String str, char ichar) {
        int i = (str.charAt(0) == ichar) ? 1 : 0;
        int l = (str.charAt(str.length() - 1) == ichar) ? str.length() - 1 : str.length();
        return str.substring(i, l);
    }

    public static String fix(String str, int len) {
        return fix(str, len, "0");
    }

    /**
     * 向前补特定字符串方式对其字符串
     */
    public static String fix(String str, int len, String replace_char) {
        return String.format(len + "d", str).replace(" ", replace_char);
    }

    /**
     * uri参数转换成json字符串
     *
     * @param uri_data
     * @return
     */
    public static JSONObject path2rpc(String uri_data) {
        JSONObject rs = null;
        // String[] _iteml = uri_data.split("\\&");
        String[] _iteml = uri_data.split("&");
        String[] _keyValue;
        JSONObject obj;
        if (_iteml.length > 0) {
            obj = new JSONObject();
            for (String s : _iteml) {
                _keyValue = s.split("=");
                if (_keyValue.length == 2) {
                    obj.put(_keyValue[0], _keyValue[1]);
                }
            }
            rs = obj;
        }
        return rs != null && !rs.isEmpty() ? rs : null;
    }

    /**
     * uri参数转换成List，保留顺序，抛弃KEY
     *
     * @param gsc_post gsc_post格式post call
     * @return
     */
    public static List<String> path2list(String gsc_post) {
        return Arrays.asList(gsc_post.substring(9).split(":,"));
    }

    private static String digits(long val, int digits) {
        long hi = 1L << (digits * 4);
        return digitsMap.toString(hi | (val & (hi - 1)), digitsMap.MAX_RADIX)
                .substring(1);
    }

    /**
     * 以62进制（字母加数字）生成19位UUID，最短的UUID
     *
     * @return
     */
    public static String shortUUID() {
        UUID uuid = UUID.randomUUID();
        return digits(uuid.getMostSignificantBits() >> 32, 8) +
                digits(uuid.getMostSignificantBits() >> 16, 4) +
                digits(uuid.getMostSignificantBits(), 4) +
                digits(uuid.getLeastSignificantBits() >> 48, 4) +
                digits(uuid.getLeastSignificantBits(), 12);
    }

    /**
     * 输出数字唯一ID，20字节
     *
     * @return
     */
    public static String numUUID() {
        return StringHelper.numCode(20);
    }
//CharMatcher.anyOf("jadk").trimFrom(sequence);trimLeading;trimTrailingFrom

    /**
     * 指定长度的数字随机字符串
     *
     * @param len
     * @return
     */
    public static String numCode(int len) {
        String chars = "0123456789";
        int maxPos = chars.length();
        StringBuilder pwd = new StringBuilder();
        for (int i = 0; i < len; i++) {
            pwd.append(chars.charAt(Double.valueOf(Math.ceil(Math.floor(Math.random() * maxPos))).intValue()));
        }
        return pwd.toString();
    }

    /**
     * 输出常用验证码，6位
     *
     * @return
     */
    public static String checkCode() {
        return numCode(6);
    }

    /**
     * 输出指定长度验证码
     *
     * @param len
     * @return
     */
    public static String checkCode(int len) {
        return numCode(len);
    }

    /**
     * @param text 模板文本
     * @param data K-V对应替换的数据组
     * @return
     */
    public static String createCodeText(String text, JSONObject data) {
        String rs = text;
        for (String obj : data.keySet()) {
            rs = rs.replaceAll("@" + obj, data.getString(obj));
        }
        return rs;
    }

    public static String randomString(int max_len) {
        int l = Random.next(max_len) + 1;
        return createRandomCode(l);
    }

    public static boolean isInvalided(String str) {
        return str == null || str.trim().length() == 0 || str.trim().equals("null") || str.trim().equals("undefined");
    }

    public static String toString(Object obj) {
        String out;
        try {
            out = obj.toString();
        } catch (Exception e) {
            try {
                out = String.valueOf(obj);
            } catch (Exception e1) {
                out = null;
            }
        }
        return out;
    }

    /**
     * 字符串数组去重
     */
    public static String[] distinct(String[] arrStr) {
        return Arrays.stream(arrStr).distinct().toArray(String[]::new);
    }

    /**
     * 首字母大写,如果已经大写不处理
     */
    public static String captureName(String str) {
        char[] v = str.toCharArray();
        if (v[0] >= 'a' && v[0] <= 'z') {
            v[0] -= 32;
        }
        return String.valueOf(v);
    }

    /**
     * 删除字符串第1个字符
     * @return
     */
    public StringHelper removeLeadingFrom() {
        str = str.length() > 0 ? str.substring(1) : "";
        return this;
    }

    /**
     * 删除字符串前面N个字符
     *
     * @param i
     * @return
     */
    public StringHelper removeLeadingFrom(int i) {
        str = (i > str.length()) ? null : str.length() > 0 ? str.substring(i) : "";
        return this;
    }

    /**
     * 删除字符串最后1个字符
     */
    public StringHelper removeTrailingFrom() {
        str = str.length() > 0 ? str.substring(0, str.length() - 1) : "";
        return this;
    }

    /**
     * 删除字符串最后N个字符
     * @param i
     * @return
     */
    public StringHelper removeTrailingFrom(int i) {
        str = (i < 1) ? str : str.length() > 0 ? str.substring(0, str.length() - i) : str;
        return this;
    }

    /**
     * 删除字符串前后第一个字符
     */
    public StringHelper removeFrom() {
        if (str.length() > 0) {
            str = str.substring(1);
        }
        if (str.length() > 0) {
            str = str.substring(0, str.length() - 1);
        }
        return this;
    }

    /**
     * 修复字符串
     * <p>
     * 删除第一个或者最后一个与ichar相同 的 字符
     * @param ichar
     * @return
     */
    public StringHelper trimFrom(char ichar) {
        int i = (str.charAt(0) == ichar) ? 1 : 0;
        int l = (str.charAt(str.length() - 1) == ichar) ? str.length() - 1 : str.length();
        str = str.substring(i, l);
        return this;
    }

    public StringHelper left(int len) {
        str = (str.length() > len) ? str.substring(0, len) : str;
        return this;
    }

    private void leftFix(char ichar) {
        int i = 0, l = str.length();
        for (; i < l; i++) {
            if (str.charAt(i) != ichar) {
                break;
            }
        }
        str = str.substring(i);
    }

    private void rightFix(char ichar) {
        int l = str.length();
        if (l > 0) {
            int i = 0;
            for (; i < l; l--) {
                if (str.charAt(l - 1) != ichar) {
                    break;
                }
            }
            str = str.substring(0, l);
        }
    }

    public StringHelper right(int len) {
        int l = str.length();
        str = (str.length() > len) ? str.substring(l - len, l) : str;
        return this;
    }

    /**
     * 删除空白
     *
     * @return
     */
    public StringHelper replaceBlank() {
        String dest = "";
        if (str != null) {
            Matcher m = pattern.matcher(str);
            dest = m.replaceAll("");
        }
        str = dest;
        return this;
    }

    /**
     * 任何情况下按行切分字符串
     *
     * @return
     */
    public String[] toArrayByLine() {
        String data = str;
        String[] formline = data.split("\n");
        if (formline.length < 2) {
            formline = data.split("\r");
        }
        for (int i = 0; i < formline.length; i++) {
            formline[i] = trimFrom('\r').trimFrom('\n').toString();
        }
        return formline;
    }

    @Override
    public String toString() {
        return str;
    }

    /**
     * 首字母大写
     *
     * @return
     */
    public StringHelper upperCaseFirst() {
        char chr = str.length() > 0 ? str.charAt(0) : '\0';
        StringBuilder strBuilder = new StringBuilder(str);
        strBuilder.setCharAt(0, String.valueOf(chr).toUpperCase().charAt(0));
        str = strBuilder.toString();
        return this;
    }

    /**
     * 获得最后一个字符
     *
     * @return
     */
    public StringHelper charAtLast() {
        charAt(str.length() - 1);
        return this;
    }

    public StringHelper charAt(int idx) {
        int len = str.length();
        str = (len > idx && idx >= 0) ? String.valueOf(str.charAt(idx)) : "";
        return this;
    }

    /**
     * 获得第一个字符
     *
     * @return
     */
    public StringHelper charAtFirst() {
        return charAt(0);
    }

    /**
     * 去掉第一个字符
     *
     * @return
     */
    public StringHelper trimLeading() {
        str = str.length() > 0 ? str.substring(1) : "";
        return this;
    }

    /**
     * 从左  开始 替换ichar对应字符
     *
     * @param ichar
     * @return
     */
    public StringHelper trimLeadingFrom(char ichar) {
        leftFix(ichar);
        return this;
    }

    /**
     * 从右  开始 替换ichar对应字符
     *
     * @param ichar
     * @return
     */
    public StringHelper trimTrailingFrom(char ichar) {
        rightFix(ichar);
        return this;
    }

    public StringHelper toUTF8(String charSetName) {
        String rString;
        try {
            rString = new String(str.getBytes(charSetName), StandardCharsets.UTF_8);
        } catch (UnsupportedEncodingException e) {
            // TODO Auto-generated catch block
            rString = "";
        }
        str = rString;
        return this;
    }

    public static Set<String> toHashSet(String text, String spilt) {
        String[] arr = text.split(spilt);
        Set<String> r = new HashSet<>();
        Collections.addAll(r, arr);
        return r;
    }

    public static List<String> toList(String text, String spilt) {
        String[] arr = text.split(spilt);
        List<String> r = new ArrayList<>();
        Collections.addAll(r, arr);
        return r;
    }

    /**
     * 不够位数的在前面补0，保留num的长度位数字
     *
     * @param num
     * @return
     */
    public String autoGenericCode(int num) {
        // 保留num的位数
        // 0 代表前面补充0
        // num 代表长度为4
        // d 代表参数为正数型
        return String.format("%0" + num + "d", Integer.parseInt(str));
    }

    public Set<String> toHashSet() {
        return toHashSet(",");
    }

    public Set<String> toHashSet(String spilt) {
        return StringHelper.toHashSet(str, spilt);
    }

    public List<String> toList() {
        return toList(",");
    }

    public List<String> toList(String spilt) {
        return toList(str, spilt);
    }

    /**
     * 获得字符模板对象
     * 将 ${key} 内字符按照输入的key-value替换
     */
    public StringTemplate toTemplate() {
        return new StringTemplate(str);
    }
}
