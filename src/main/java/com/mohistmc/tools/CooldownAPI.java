package com.mohistmc.tools;

import java.time.Duration;
import java.time.LocalDateTime;

/**
 * Use LocalDateTime
 */
public record CooldownAPI(LocalDateTime old, LocalDateTime Now) {

    public boolean isAfter() {
        return Now.isAfter(old);
    }

    public long timeLeft() {
        Duration duration = Duration.between(LocalDateTime.now(), old);
        return duration.getSeconds();
    }
}
