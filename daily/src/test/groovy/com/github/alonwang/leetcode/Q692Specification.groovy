package com.github.alonwang.leetcode

import spock.lang.Specification

/**
 * @author alonwang* @date 2020/6/1 11:39 下午
 * @detail
 */
class Q692Specification extends Specification {
    def "test"() {
        expect:
        new Q692().topKFrequent(arr as String[], k as int) == result
        where:
        arr                                              | k | result
        ["i", "love", "leetcode", "i", "love", "coding"] | 2 | ["i", "love"]
        ["i", "love", "leetcode", "i", "love", "coding"] | 3 | ["i", "love", "coding"]
        ["a", "aa", "aaa"]                               | 1 | ["a"]

    }
}
