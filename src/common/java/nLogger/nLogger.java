package common.java.nLogger;

public class nLogger {
    public static LoggerOut clientFunc = null;
    private static boolean isDebug = true;

    public static void onLogger(LoggerOut event) {
        clientFunc = event;
    }

    public static void setDebug(boolean state) {
        isDebug = state;
    }

    private static void out(String info, LogType.InfoType type) {
        if (clientFunc != null) {
            clientFunc.out(info, type);
        }
    }

    private static String getLogInfo(Exception e, LogType.InfoType type, String in) {
        LogInfo infoObj = e == null ? LogInfo.build() : LogInfo.build(e);
        return infoObj.level(type)
                .info(in)
                .toString();
    }

    private static String getLogInfo(Throwable e, LogType.InfoType type, String in) {
        LogThrow infoObj = e == null ? LogThrow.build() : LogThrow.build(e);
        return infoObj.level(type)
                .info(in)
                .toString();
    }

    public static void debugInfo(Exception e) {
        debugInfo(e, null);
    }

    public static void debugInfo(String in) {
        debugInfo(null, in);
    }

    public static void debugInfo(Throwable in) {
        debugInfo(in, null);
    }

    public static void debugInfo(Exception e, String in) {
        if (!isDebug) {
            return;
        }
        String info = getLogInfo(e, LogType.InfoType.DEBUG, in);
        System.out.println(info);
        out(info, LogType.InfoType.DEBUG);
    }

    public static void debugInfo(Throwable e, String in) {
        if (!isDebug) {
            return;
        }
        String info = getLogInfo(e, LogType.InfoType.DEBUG, in);
        System.out.println(info);
        out(info, LogType.InfoType.DEBUG);
    }

    public static void logInfo(Exception e) {
        logInfo(e, null);
    }

    public static void logInfo(Throwable e) {
        logInfo(e, null);
    }

    public static void logInfo(String in) {
        logInfo(null, in);
    }

    public static void logInfo(Exception e, String in) {
        String info = getLogInfo(e, LogType.InfoType.LOG, in);
        if (isDebug) {
            System.out.println(info);
        }
        out(info, LogType.InfoType.LOG);
    }

    public static void logInfo(Throwable e, String in) {
        String info = getLogInfo(e, LogType.InfoType.LOG, in);
        if (isDebug) {
            System.out.println(info);
        }
        out(info, LogType.InfoType.LOG);
    }

    public static void warnInfo(Exception e) {
        warnInfo(e, null);
    }

    public static void warnInfo(Throwable e) {
        warnInfo(e, null);
    }

    public static void warnInfo(String in) {
        warnInfo(null, in);
    }

    public static void warnInfo(Exception e, String in) {
        System.out.println(getLogInfo(e, LogType.InfoType.WARN, in));
    }

    public static void warnInfo(Throwable e, String in) {
        System.out.println(getLogInfo(e, LogType.InfoType.WARN, in));
    }

    public static void errorInfo(Exception e) {
        errorInfo(e, null);
    }

    public static void errorInfo(Throwable e) {
        errorInfo(e, null);
    }

    public static void errorInfo(String in) {
        errorInfo(null, in);
    }

    public static void errorInfo(Exception e, String in) {
        String info = getLogInfo(e, LogType.InfoType.ERROR, in);
        System.out.println(info);
        out(info, LogType.InfoType.ERROR);
    }

    public static void errorInfo(Throwable e, String in) {
        String info = getLogInfo(e, LogType.InfoType.ERROR, in);
        System.out.println(info);
        out(info, LogType.InfoType.ERROR);
    }
}