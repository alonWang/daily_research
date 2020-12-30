package com.github.alonwang.util

import spock.lang.Specification

/**
 * @author alonwang* @date 2020/12/5 12:59 下午
 */
class DataPeriodicResetterTest extends Specification {


    def "test update"() {
        given:
        OneSecondsResetter resetter = DataPeriodicResetter.create(OneSecondsResetter.class)
        OneSecondsResetter.Pojo pojo = resetter.get()
        pojo.a = 100;
        expect:
        resetter.update()
        resetter.get().a == 100
        Thread.sleep(2000)
        resetter.get().a == 0
    }
    def "test set"() {
        given:
        OneSecondsResetter resetter = DataPeriodicResetter.create(OneSecondsResetter.class)
        OneSecondsResetter.Pojo pojo = resetter.get()
        pojo.a = 100;
        expect:
        resetter.update()
        resetter.set(pojo)
        Thread.sleep(2000)
        resetter.get().a == 0
    }

}
