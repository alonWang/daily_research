package com.github.alonwang.algorithm4

import spock.lang.Specification

class Chapter1Test extends Specification {

    def "test binary search"() {
        expect: "right"
        def targets = (int[]) Arrays.asList(1, 5, 6, 8, 11, 18).toArray()
        def rank = BinarySearch.rank(8, targets)
        rank == 3
    }
}
