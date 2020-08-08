package com.github.alonwang.util

import spock.lang.Specification

import java.util.concurrent.TimeUnit

/**
 * @author alonwang* @date 2020/8/8 4:46 下午
 * @detail
 */
class DailyCounterSpecification extends Specification {

    def "test"() {
        given:
        DailyCounter counter = new DailyCounter(System.currentTimeMillis());
        expect:
        true == counter.incr(2);
        true == counter.incr(2);
        false == counter.incr(2);
    }

    def "test change day"() {
        given:
        DailyCounter counter = new DailyCounter(1, System.currentTimeMillis() - TimeUnit.DAYS.toMillis(1));
        expect:
        true == counter.incr(1)
    }
}
