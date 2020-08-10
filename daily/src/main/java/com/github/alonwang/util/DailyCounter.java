package com.github.alonwang.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * 每日计数限制器
 *
 * @author alonwang
 * @date 2020/8/7 5:18 下午
 * @detail
 */
public class DailyCounter {
    private int count;
    private long timestamp;

    public DailyCounter() {
    }

    public DailyCounter(long timestamp) {
        this.timestamp = timestamp;
    }

    public DailyCounter(int count, long timestamp) {
        this.count = count;
        this.timestamp = timestamp;
    }

    /**
     * 如果当天次数小于limit,成功并次数+1，否则失败。
     *
     * @param limit >0
     * @return true 成功，false 添加失败。
     */
    public boolean incrOrFail(int limit) {
        assert limit > 0;
        int oldCount = count();
        if (oldCount >= limit) {
            return false;
        }
        count = ++oldCount;
        timestamp = System.currentTimeMillis();
        return true;
    }

    /**
     * 是否增加
     *
     * @param limit
     * @return
     */

    public boolean canIncr(int limit) {
        assert limit > 0;
        int oldCount = count();
        return oldCount < limit;
    }


    public int count() {
        return isSameDay() ? count : 0;
    }

    private boolean isSameDay() {
        LocalDate oldDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault()).toLocalDate();
        if (LocalDate.now().equals(oldDate)) {
            return true;
        }
        return false;
    }

    public int getCount() {
        return count;
    }

    public long getTimestamp() {
        return timestamp;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public void setTimestamp(long timestamp) {
        this.timestamp = timestamp;
    }
}
