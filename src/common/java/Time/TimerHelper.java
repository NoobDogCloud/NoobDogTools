package common.java.Time;

import common.java.Thread.ThreadHelper;

import java.util.concurrent.TimeUnit;
import java.util.function.Supplier;

/**
 * 定时时间
 */
public class TimerHelper {
    public static void schedule(Runnable task, long delay_seconds) {
        var delay = TimeUnit.SECONDS.toMillis(delay_seconds);
        while (true) {
            try {
                task.run();
            } catch (Exception e) {
                break;
            }
            ThreadHelper.sleep(delay);
        }
    }

    /**
     * @param task          任务
     * @param delay_seconds 延迟时间
     * @param time_out      超时时间
     * @return 是否超时
     * @apiNote 定时任务, 支持超时中断
     */
    public static boolean schedule(Supplier<Boolean> task, long delay_seconds, long time_out) {
        long total_count = time_out / delay_seconds;
        var delay = TimeUnit.SECONDS.toMillis(delay_seconds);
        for (int i = 0; i < total_count; i++) {
            if (task.get()) {
                return true;
            }
            ThreadHelper.sleep(delay);
        }
        return false;
    }
}
