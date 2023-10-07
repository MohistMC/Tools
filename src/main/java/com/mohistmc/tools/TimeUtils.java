package com.mohistmc.tools;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * @author Mgazul by MohistMC
 * @date 2023/7/20 10:00:34
 */
public class TimeUtils {
    public static String main(long duration) {

        // 将long值转换为Duration对象
        Duration dur = Duration.ofSeconds(duration);

        // 将Duration对象转换为LocalDateTime对象
        LocalDateTime dateTime = LocalDateTime.now().plus(dur);

        // 计算剩余的秒数
        int remainingSeconds = (int) (dur.getSeconds() % 60);

        // 计算剩余的分钟数
        int remainingMinutes = (int) ((dur.getSeconds() / 60) % 60);

        // 计算剩余的小时数
        int remainingHours = (int) ((dur.getSeconds() / (60 * 60)) % 24);

        // 计算剩余的天数
        long remainingDays = (dur.getSeconds() / (24 * 60 * 60)) % 365;

        // 输出结果
        if (remainingDays != 0) {
            return remainingDays + "天" + remainingHours + "时" + remainingMinutes + "分" + remainingSeconds + "秒";
        } else if (remainingHours != 0) {
            return remainingHours + "时" + remainingMinutes + "分" + remainingSeconds + "秒";
        }else if (remainingMinutes != 0) {
            return remainingMinutes + "分" + remainingSeconds + "秒";
        } else if (remainingSeconds != 0) {
            return remainingSeconds + "秒";
        }
        return "§c无";
    }
}
