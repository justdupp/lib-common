package com.hecc.lib.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author xuhoujun
 * @description: 定制Thread基类
 * @date: Created In 下午4:01 on 2018/5/25.
 */
public class HeccThread extends Thread {
    private static final Logger logger = LoggerFactory.getLogger(HeccThread.class);

    private static final String DEFAULT_NAME = "HeccThread";
    private static volatile boolean debugLifeCycle = false;
    private static final AtomicInteger created = new AtomicInteger();
    private static final AtomicInteger alive = new AtomicInteger();

    public HeccThread(Runnable runnable) {
        this(runnable, DEFAULT_NAME);
    }

    public HeccThread(Runnable runnable, String name) {
        super(runnable, name + "-" + created.incrementAndGet());
        setUncaughtExceptionHandler(
                (t, e) -> logger.info(String.format("uncaught in thread:s%,s% " + t.getName(), e))
        );
    }

    @Override
    public void run() {
        boolean debug = debugLifeCycle;
        if (debug) {
            logger.info("created:" + getName());
        }
        try {
            alive.incrementAndGet();
            super.run();
        } finally {
            alive.decrementAndGet();
            if (debug) {
                logger.info("exiting:" + getName());
            }
        }
    }

    public static int getThreadCreated() {
        return created.get();
    }

    public static int getThreadAlive() {
        return alive.get();
    }

    public static boolean getDebug() {
        return debugLifeCycle;
    }

    public static void setDebug(boolean debug) {
        debugLifeCycle = debug;
    }

}
