package common.java.nLogger;

import common.java.Time.TimeHelper;
import org.json.gsc.JSONObject;

import java.io.PrintWriter;
import java.io.StringWriter;

public class LogInfo {
    private final Exception e;
    private LogType.InfoType type;
    private String info;

    private LogInfo(Exception e, LogType.InfoType type) {
        this.e = e;
        this.type = type;
        if (this.e != null) {
            this.info = this.e.getMessage();
        }
    }

    public static LogInfo build() {
        return new LogInfo(null, LogType.InfoType.LOG);
    }

    public static LogInfo build(Exception e) {
        return new LogInfo(e, LogType.InfoType.LOG);
    }

    public static LogInfo build(Exception e, LogType.InfoType type) {
        return new LogInfo(e, type);
    }

    public LogInfo level(LogType.InfoType type) {
        this.type = type;
        return this;
    }

    public LogInfo info(String in) {
        this.info = in;
        return this;
    }

    private String stack(Exception e) {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }

    public String toString() {
        String rs = "";
        rs += "[" + TimeHelper.build().nowDatetime() + "]";
        rs += "[" + this.type.toString() + "]";
        rs += "[" + Thread.currentThread().getId() + "]";
        rs += "[" + this.info + "]";
        if (e != null) {
            rs += "================================\n";
            rs += stack(e);
        }
        return rs;
    }

    public JSONObject toJson() {
        JSONObject rs = JSONObject.build("time", TimeHelper.build().nowDatetime())
                .put("LEVEL", type.toString())
                .put("ThreadID", Thread.currentThread().threadId())
                .put("message", this.info);
        if (e != null) {
            rs.put("stack", stack(e));
        }
        return rs;
    }


}
