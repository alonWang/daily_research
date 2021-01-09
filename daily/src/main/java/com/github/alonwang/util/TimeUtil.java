package com.github.alonwang.util;

import com.google.common.collect.Range;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;

/**
 * @author alonwang
 * @date 2021/1/4 21:57
 */
public class TimeUtil {
    public static final long MILLIS_ONE_DAY = 24 * 60 * 60 * 1000;
    /**
     * 今日时间范围,[今日开始时间戳,今日结束时间戳)
     */
    private static final ThreadLocal<Range<Long>> todayTimeRangeCache = ThreadLocal.withInitial(TimeUtil::createTodayTimeRange);

    /**
     * 获取今日0点
     * @return
     */
    public static long getTodayZeroTime() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.MIN).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    private static Range<Long> createTodayTimeRange() {
        long todayStartTime = getTodayZeroTime();
        long todayEndTime = todayStartTime + MILLIS_ONE_DAY;
        return Range.closedOpen(todayStartTime, todayEndTime);
    }

    /**
     * 传入时间是否是今天
     *
     * @param timeMillis
     * @return
     */
    public static boolean isToday(long timeMillis) {
        long now = System.currentTimeMillis();
        Range<Long> todayTimeRange = todayTimeRangeCache.get();
        if (!todayTimeRange.contains(now)) {
            Range<Long> timeRange = createTodayTimeRange();
            todayTimeRangeCache.set(timeRange);
            todayTimeRange = timeRange;
        }
        return todayTimeRange.contains(timeMillis);
    }
}
