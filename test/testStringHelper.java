import common.java.String.StringHelper;
import junit.framework.TestCase;
import org.json.gsc.JSONObject;
import org.junit.Test;

public class testStringHelper extends TestCase {
    @Test
    public void testStringTemplate() {
        JSONObject value = JSONObject.build().put("user", "tester").put("pass@word", 123);
        String template1 = "${user}是用户,${pass@word}";
        String s = StringHelper.build(template1).toTemplate().replace(value).toString();
        assertEquals(s, "tester是用户,123");
        template1 = "that it !${user}是用户,${pass@word}";
        s = StringHelper.build(template1).toTemplate().replace(value).toString();
        assertEquals(s, "that it !tester是用户,123");
        template1 = "that it !${user}是用户,${pass@word}zero";
        s = StringHelper.build(template1).toTemplate().replace(value).toString();
        assertEquals(s, "that it !tester是用户,123zero");
    }
}
