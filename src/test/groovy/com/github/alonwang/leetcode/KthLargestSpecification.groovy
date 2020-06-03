package com.github.alonwang.leetcode

import spock.lang.Specification

/**
 * @author alonwang* @date 2020/6/3 9:30 下午
 * @detail
 */
class KthLargestSpecification extends Specification {
    def test() {
        expect:
        def obj = new KthLargest(k as int, nums as int[])
        map.each { entry -> obj.add(entry.key) == entry.value }

        where:
        k | nums         | map
        3 | [4, 5, 8, 2] | [3: 4, 5: 5, 10: 5, 9: 8, 4: 8]
    }
}
