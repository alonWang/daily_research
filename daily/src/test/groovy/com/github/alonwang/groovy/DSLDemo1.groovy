package com.github.alonwang.groovy

/**
 * @author alonwang* @date 2020/6/4 7:45 上午
 * @detail
 */
class DSLDemo1 {


    def static please(action) {
        [the: {
            what -> [of: { n -> action(what(n)) }]
        }]
    }

    static void main(String[] args) {
        def show = { print it }
        def square_root = { Math.sqrt(it) }
        please(show).the(square_root).of(100)
        println()
        please show the square_root of 100
    }
}
