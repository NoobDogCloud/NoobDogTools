package common.java.nLogger;

public class LogType {
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

