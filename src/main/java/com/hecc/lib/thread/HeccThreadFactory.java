package com.hecc.lib.thread;

import java.util.concurrent.ThreadFactory;

/**
 * @author xuhoujun
 * @description: 自定义线程工厂
 * @date: Created In 下午3:59 on 2018/5/25.
 */
public class HeccThreadFactory implements ThreadFactory {
    private final String pollName;

    public HeccThreadFactory(String pollName) {
        this.pollName = pollName;
    }

    @Override
    public Thread newThread(Runnable r) {
        return new HeccThread(r, pollName);
    }
}
