package common.java.nLogger;

import common.java.Time.TimeHelper;
import org.json.gsc.JSONObject;

import java.io.PrintWriter;
import java.io.StringWriter;

public class LogThrow {
    private final Throwable e;
    private LogThrow.InfoType type;
    private String info;

    private LogThrow(Throwable e, LogThrow.InfoType type) {
        this.e = e;
        this.type = type;
        if (this.e != null) {
            this.info = this.e.getMessage();
        }
    }

    public static LogThrow build() {
        return new LogThrow(null, LogThrow.InfoType.LOG);
    }

    public static LogThrow build(Throwable e) {
        return new LogThrow(e, LogThrow.InfoType.LOG);
    }

    public static LogThrow build(Throwable e, LogThrow.InfoType type) {
        return new LogThrow(e, type);
    }

    public LogThrow level(LogThrow.InfoType type) {
        this.type = type;
        return this;
    }

    public LogThrow info(String in) {
        this.info = in;
        return this;
    }

    private String stack(Throwable e) {
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
                .put("ThreadID", Thread.currentThread().getId())
                .put("message", this.info);
        if (e != null) {
            rs.put("stack", stack(e));
        }
        return rs;
    }

    public enum InfoType {
        LOG("Log"), WARN("Warn"), ERROR("Error"), DEBUG("Debug");
        private final String text;

        InfoType(String in) {
            text = in;
        }

        public String toString() {
            return text;
        }
    }
}
