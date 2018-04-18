package com.hecc.lib.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;
import java.util.concurrent.Callable;

/**
 * @author xuhoujun
 * @description: 字符工具类
 * @date: Created In 下午8:28 on 2018/4/18.
 */
public class IllegalCharacterFilterThread implements Callable<String> {

    private static final Logger logger = LoggerFactory.getLogger(IllegalCharacterFilterThread.class);

    private static boolean stopped;
    private String msg;
    private List<String> list;

    public IllegalCharacterFilterThread(String msg, List<String> list) {
        stopped = true;
        this.msg = msg;
        this.list = list;
    }

    synchronized void stop() {
        stopped = false;
    }

    @Override
    public String call() throws Exception {
        StringBuilder illegalKeyWord = new StringBuilder();
        String messages = msg;
        for (String str : list) {
            if (messages.indexOf(str) > -1) {
                logger.info("已经匹配到非法字符：" + str + ",[" + msg + "]");
                if (illegalKeyWord.length() > 1) {
                    illegalKeyWord.append("," + str);
                } else {
                    illegalKeyWord.append(str);
                }
            }
        }
        return illegalKeyWord.toString();
    }
}
