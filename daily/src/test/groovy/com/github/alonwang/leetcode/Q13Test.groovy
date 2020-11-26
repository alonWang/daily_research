package com.github.alonwang.leetcode

import spock.lang.Specification

/**
 * *@author alonwang
 * @date 2020/11/26 12:10
 */
class Q13Test extends Specification {
    def "test "() {
        expect:
        new Q13().romanToInt(romanStr) == result
        where:
        romanStr | result
        "III"    | 3
        "IX"     | 9
        "LVIII"  | 58

    }
}
