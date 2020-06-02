package com.github.alonwang.leetcode

import spock.lang.Specification

/**
 * @author alonwang* @date 2020/6/2 20:58
 * @detail
 */
class Q215Specification extends Specification {

    def "test"() {
        expect:
        new Q215().findKthLargest(nums as int[], k as int) == result
        where:
        nums                        | k | result
        [3, 2, 1, 5, 6, 4]          | 2 | 5
        [3, 2, 3, 1, 2, 4, 5, 5, 6] | 4 | 4
    }
}
