package common.java.Performance;

import common.java.Time.TimeHelper;

public class DebugPerformance {
    private final String taskName;
    private final TimeHelper timeHelper = TimeHelper.build();
    private long startTime;

    public DebugPerformance() {
        taskName = "";
        startTime = timeHelper.nowMillis();
    }

    public DebugPerformance(String _taskName) {
        taskName = _taskName;
        startTime = timeHelper.nowMillis();
    }

    public long end() {
        long rs = (timeHelper.nowMillis() - startTime);
        System.out.println("TaskName:" + taskName + "timeElapsed:" + rs);
        startTime = timeHelper.nowMillis();
        return rs;
    }
}
