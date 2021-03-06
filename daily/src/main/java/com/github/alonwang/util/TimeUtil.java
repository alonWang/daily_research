package com.github.alonwang.util;

import com.alibaba.fastjson.JSON;
import com.github.alonwang.lang.generic.TypeHelper;
import com.google.common.collect.Range;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.util.HashMap;
import java.util.Map;

/**
 * @author alonwang
 * @date 2021/1/4 21:57
 */
public class TimeUtil {
    public static final long MILLIS_ONE_DAY = 24 * 60 * 60 * 1000;
    /**
     * 今日时间范围,[今日开始时间戳,今日结束时间戳)
     */
    private static final ThreadLocal<Range<Long>> todayTimeRangeCache =
            ThreadLocal.withInitial(TimeUtil::createTodayTimeRange);

    /**
     * 获取今日0点
     *
     * @return
     */
    public static long getTodayZeroMillis() {
        return LocalDateTime.of(LocalDate.now(), LocalTime.MIN).atZone(ZoneId.systemDefault()).toInstant().toEpochMilli();
    }

    /**
     * 传入时间是否属于今天
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

    private static Range<Long> createTodayTimeRange() {
        long todayStartTime = getTodayZeroMillis();
        long todayEndTime = todayStartTime + MILLIS_ONE_DAY;
        return Range.closedOpen(todayStartTime, todayEndTime);
    }

    public static void main(String[] args) {
        Map<Integer,Integer> a= JSON.parseObject("", new TypeHelper<HashMap<Integer,Integer>>().getType());
        System.out.println(a);
    }
}
