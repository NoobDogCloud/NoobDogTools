package common.java.Random;

public class Random {
    private final static java.util.Random random = new java.util.Random();

    public static int getRandom(int min, int max) {
        return random.nextInt(max - min + 1) + min;
    }

    public static String getRandomString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; i++) {
            sb.append(getRandomChar());
        }
        return sb.toString();
    }

    public static char getRandomChar() {
        int rand = getRandom(0, 61);
        if (rand < 10) {
            return (char) ('0' + rand);
        } else if (rand < 36) {
            return (char) ('A' + rand - 10);
        } else {
            return (char) ('a' + rand - 36);
        }
    }

    public static int next() {
        return random.nextInt();
    }

    public static int next(int bound) {
        return random.nextInt(bound);
    }

    public static int next(int origin, int bound) {
        return random.nextInt(bound) + origin;
    }

    public static int next(int origin, int bound, int step) {
        return random.nextInt(bound - origin) / step * step + origin;
    }

}
