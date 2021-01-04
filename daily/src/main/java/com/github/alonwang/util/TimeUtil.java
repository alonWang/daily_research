package com.github.alonwang.util;

import com.google.common.collect.Range;

import java.time.LocalDateTime;

/**
 * @author alonwang
 * @date 2021/1/4 21:57
 */
public class TimeUtil {
    public static final long MILLIS_ONE_DAY=24*60*60*1000;
    /**
     * 今日时间范围,[今日开始时间戳,今日结束时间戳)
     */
    private static ThreadLocal<Range<Long>> todayTimeRangeCache = ThreadLocal.withInitial(TimeUtil::createTodayTimeRange);

    public static long getTodayZeroTime() {
        //TODO 以后实现
        return -1;
    }

    private static Range<Long> createTodayTimeRange() {
        long todayStartTime = getTodayZeroTime();
        long todayEndTime =todayStartTime+MILLIS_ONE_DAY;
      return   Range.closedOpen(todayStartTime,todayEndTime);
    }

    /**
     * 传入时间与今天是否是同一天
     * @param timeMillis
     * @return
     */
    public static boolean isSameDayQuick(long timeMillis){
        long now=System.currentTimeMillis();
        Range<Long> todayTimeRange=todayTimeRangeCache.get();
        if (!todayTimeRange.contains(now)){
            Range<Long> timeRange=createTodayTimeRange();
            todayTimeRangeCache.set(timeRange);
            todayTimeRange=timeRange;
        }
        return todayTimeRange.contains(timeMillis);
    }
}
