package com.github.alonwang.algorithm4

import spock.lang.Specification

class Chapter1Test extends Specification {

    def "test binary search"() {
        when: "given a sorted array"
        def sources = (int[]) Arrays.asList(1, 5, 6, 8, 11, 18).toArray()
        def number = 8
        then: "number #number is #rank"
        def rank = BinarySearch.rank(number, sources)
        rank == 3
    }

    def "test two sum fast"() {
        when: "given a sorted array"
        def sources = (int[]) Arrays.asList(1, 7, 9, 67, 56, 456, 76, 87, -1, -7, -67, -76)

        then: "count of sum is 0 is #count"
        def count = TwoSumFast.count(sources)
        count == 4
    }
}
