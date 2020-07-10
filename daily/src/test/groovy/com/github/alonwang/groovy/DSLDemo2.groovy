package com.github.alonwang.groovy
/** calculate sum of 1 + 2 is 3
 * @author alonwang* @date 2020/6/5 7:56 上午
 * @detail
 */
class DSLDemo2 {
    def static please(show) {
        [the: { action ->
            [of: { a ->
                [and: {
                    b -> show(action(a, b))
                }]
            }]
        }]
    }


    static void main(String[] args) {
        def show = { println it }
        def plus = { a, b -> a + b }
        def minus = { a, b -> a - b }
        def multi = { a, b -> a * b }
        def devide = { a, b -> a / b }
        please show the plus of 1 and 2
        please show the minus of 1 and 2
        please show the multi of 1 and 2
        please show the devide of 1 and 2


    }

}
