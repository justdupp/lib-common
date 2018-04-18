package com.hecc.lib.util;

import java.util.concurrent.locks.StampedLock;
import java.util.function.Function;
import java.util.function.Supplier;

/**
 * @author xuhoujun
 * @description: 并发控制锁工具类
 * @date: Created In 下午9:10 on 2018/4/18.
 */
public class LockUtil {

    private LockUtil() {
    }

    /**
     * 读写锁
     *
     * @param obtainReadWriteLock    获取读写锁
     * @param readStatement          悲观读的方法
     * @param needUpgradeToWriteLock 是否需要写锁
     * @param writeStatement         写的方法
     * @param obtainResult           获取返回值
     * @param <TReaderResult>        读方法返回值类型
     * @param <TResult>              整个方法返回值类型
     * @return 返回值
     */
    public static <TResult, TReaderResult> TResult withReadWriteLockPessimistic(
            Supplier<StampedLock> obtainReadWriteLock,
            Supplier<? extends TReaderResult> readStatement,
            Function<? super TReaderResult, Boolean> needUpgradeToWriteLock,
            Function<? super TReaderResult, ? extends TReaderResult> writeStatement,
            Function<? super TReaderResult, ? extends TResult> obtainResult) {
        if (obtainReadWriteLock == null || readStatement == null
                || needUpgradeToWriteLock == null || writeStatement == null || obtainResult == null) {
            throw new NullPointerException();
        }
        final StampedLock syncLock = obtainReadWriteLock.get();
        long mainLock = syncLock.readLock();
        try {
            TReaderResult tempResult = readStatement.get();
            if (needUpgradeToWriteLock.apply(tempResult)) {
                while (true) {
                    long upgradeLock = syncLock.tryConvertToWriteLock(mainLock);
                    if (upgradeLock != 0L) {
                        mainLock = upgradeLock;
                        tempResult = writeStatement.apply(tempResult);
                        break;
                    }
                    syncLock.unlockRead(mainLock);
                    mainLock = syncLock.writeLock();
                }
            }
            return obtainResult.apply(tempResult);
        } finally {
            syncLock.unlock(mainLock);
        }
    }

    /**
     * 写锁
     *
     * @param obtainLock     获取读写锁
     * @param writeStatement 写的方法
     * @param <TResult>      返回类型
     * @return 返回值
     */
    public static <TResult> TResult withWriteLock(Supplier<StampedLock> obtainLock,
            Supplier<? extends TResult> writeStatement) {
        if (obtainLock == null || writeStatement == null) {
            throw new NullPointerException();
        }
        final StampedLock syncLock = obtainLock.get();
        final long mainLock = syncLock.writeLock();
        try {
            return writeStatement.get();
        } finally {
            syncLock.unlockWrite(mainLock);
        }
    }

    /**
     * 写锁
     *
     * @param obtainLock     获取读写锁
     * @param writeStatement 写的方法
     */
    public static void withWriteLock(Supplier<StampedLock> obtainLock,
                                     Action writeStatement) {
        if (obtainLock == null || writeStatement == null) {
            throw new NullPointerException();
        }
        final StampedLock syncLock = obtainLock.get();
        final long mainLock = syncLock.writeLock();
        try {
            writeStatement.apply();
        } finally {
            syncLock.unlockWrite(mainLock);
        }
    }

}
