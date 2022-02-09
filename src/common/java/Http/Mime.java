package common.java.Http;

import gfw.Magic;
import gfw.MagicMatch;

import java.io.File;

public class Mime {
    public static final String defaultMime = "text/plain";

    public static String getMime(byte[] buff) {
        try {
            MagicMatch match = Magic.getMagicMatch(buff);
            return match.getMimeType();
        } catch (Exception e) {
            return defaultMime;
        }
    }

    public static String getMime(File file) {
        try {
            MagicMatch match = Magic.getMagicMatch(file, true);
            return match.getMimeType();
        } catch (Exception e) {
            return defaultMime;
        }
    }
}
