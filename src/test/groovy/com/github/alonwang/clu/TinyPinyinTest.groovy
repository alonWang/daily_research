package com.github.alonwang.clu

import com.github.promeg.pinyinhelper.Pinyin
import spock.lang.Specification

/**
 * @description: ${description}*
 * @author: alonwang*
 * @create: 2019-12-06 11:25
 * */
class TinyPinyinTest extends Specification {
    def "basic"() {
        expect:
        def pinyin = Pinyin.toPinyin((char) '阿')
        pinyin.equals("A")
    }
}
