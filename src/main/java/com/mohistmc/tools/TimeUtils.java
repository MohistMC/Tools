package com.mohistmc.tools;

import java.time.Duration;

/**
 * @author Mgazul by MohistMC
 * @date 2023/7/20 10:00:34
 */
public class TimeUtils {

    public static String formatDuration(long duration) {
        Duration dur = Duration.ofSeconds(duration);
        int remainingSeconds = (int) (dur.getSeconds() % 60);
        int remainingMinutes = (int) ((dur.getSeconds() / 60) % 60);
        int remainingHours = (int) ((dur.getSeconds() / (60 * 60)) % 24);
        long remainingDays = (dur.getSeconds() / (24 * 60 * 60)) % 365;
        if (remainingDays != 0) {
            return remainingDays + "天" + remainingHours + "时" + remainingMinutes + "分" + remainingSeconds + "秒";
        } else if (remainingHours != 0) {
            return remainingHours + "时" + remainingMinutes + "分" + remainingSeconds + "秒";
        } else if (remainingMinutes != 0) {
            return remainingMinutes + "分" + remainingSeconds + "秒";
        } else if (remainingSeconds != 0) {
            return remainingSeconds + "秒";
        }
        return "§c无";
    }
}
