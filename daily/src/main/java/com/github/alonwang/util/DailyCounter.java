package com.github.alonwang.util;

import lombok.Getter;
import lombok.Setter;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;

/**
 * 计数，每日重置
 *
 * @author alonwang
 * @date 2020/8/7 5:18 下午
 * @detail
 */
@Getter
@Setter
public class DailyCounter {
    private int count;
    private long timestamp;

    public int count() {
        return isSameDay() ? count : 0;
    }

    public DailyCounter() {
        this.timestamp = System.currentTimeMillis();
    }

    public DailyCounter(long timestamp) {
        this.timestamp = timestamp;
    }

    public DailyCounter(int count, long timestamp) {
        this.count = count;
        this.timestamp = timestamp;
    }

    public boolean incr(int limit) {
        int tempCount = count();
        if (tempCount >= limit) {
            return false;
        }
        count = ++tempCount;
        timestamp = System.currentTimeMillis();
        return true;
    }

    private boolean isSameDay() {
        LocalDate oldDate = LocalDateTime.ofInstant(Instant.ofEpochMilli(timestamp), ZoneId.systemDefault()).toLocalDate();
        if (LocalDate.now().equals(oldDate)) {
            return true;
        }
        return false;
    }
}
