package com.github.alonwang.clu

import com.github.alonwang.clu.idiom.IdiomManager
import spock.lang.Specification

/**
 * @description: ${description}*
 * @author: alonwang*
 * @create: 2019-12-06 15:23
 * */
class IdiomManagerTest extends Specification {

    def setupSpec() {
        IdiomManager.init()
    }

    def "next test"() {
        expect:
        def word = IdiomManager.next(null)
        word != null
    }
}
