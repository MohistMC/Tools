package com.mohistmc.tools;

import java.util.concurrent.ThreadFactory;

/**
 * @Author Mgazul
 * @create 2019/9/11 20:57
 */
public record NamedThreadFactory(String name) implements ThreadFactory {
    private static int id = 0;

    @Override
    public Thread newThread(Runnable r) {
        Thread thread = new Thread(r);
        thread.setName(name + " - " + (++id));
        thread.setPriority(4);
        return thread;
    }
}
