package common.java.nLogger;

@FunctionalInterface
public interface LoggerOut {
    void out(String info, LogType.InfoType type);
}
