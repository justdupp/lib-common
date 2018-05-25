package com.hecc.lib.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;

/**
 * @author xuhoujun
 * @description: 含有日志和计时功能的线程池
 * @date: Created In 下午4:38 on 2018/5/25.
 */
public class TimingThreadPool extends ThreadPoolExecutor {

    private static final Logger logger = LoggerFactory.getLogger(TimingThreadPool.class);
    private final ThreadLocal<Long> startTime = new ThreadLocal<>();
    private final AtomicLong tasksNum = new AtomicLong();
    private final AtomicLong totalTime = new AtomicLong();

    public TimingThreadPool(int corePoolSize, int maximumPoolSize, long keepAliveTime, TimeUnit unit, BlockingQueue<Runnable> workQueue) {
        super(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue);
    }

    @Override
    protected void beforeExecute(Thread t, Runnable r) {
        super.beforeExecute(t, r);
        logger.info(String.format("Thread %s: start %s", t, r));
        startTime.set(System.nanoTime());
    }

    @Override
    protected void afterExecute(Runnable r, Throwable t) {
        try {
            long endTime = System.nanoTime();
            long taskTime = endTime - startTime.get();
            tasksNum.incrementAndGet();
            totalTime.addAndGet(taskTime);
            logger.info("Thread %s: end %s,time=%dns", t, r, taskTime);
        } finally {
            super.afterExecute(r, t);
        }
    }

    @Override
    protected void terminated() {
        try {
            logger.info(String.format("Terminated: avg time=%dns", totalTime.get() / tasksNum.get()));
        } finally {
            super.terminated();
        }
    }
}
